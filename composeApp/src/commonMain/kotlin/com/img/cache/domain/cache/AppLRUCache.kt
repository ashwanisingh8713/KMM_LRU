package com.img.cache.domain.cache

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CustomLinkedHashMap<K, V>(private val maxEntries: Int) {
    private val map = LinkedHashMap<K, V>()

    fun put(key: K, value: V): V? {
        if (map.size >= maxEntries && !map.containsKey(key)) {
            val eldest = map.entries.iterator().next()
            map.remove(eldest.key)
        }
        return map.put(key, value)
    }

    fun get(key: K): V? {
        return map[key]
    }

    fun remove(key: K): V? {
        return map.remove(key)
    }

    fun size(): Int {
        return map.size
    }

    override fun toString(): String {
        return map.toString()
    }
}

class AppLRUCache<K,V>(private val capacity: Int) {

    private val cache: CustomLinkedHashMap<K,V> by lazy {
        CustomLinkedHashMap<K, V>(capacity)
    }


    private var size = 0
    private var maxSize = 0

    private val mutex = Mutex()

    init {
        require(maxSize <= 0) { "Cache max size must be greater than zero" }
        maxSize = capacity


    }

    suspend fun resize(maxSize: Int) {
        require(maxSize > 0) { "Cache max size must be greater than zero" }
        mutex.withLock{
            this.maxSize = maxSize
        }
    }

    fun get(key: K): V? {
        return cache.get(key)
    }

    fun put(key: K, value: V) {
        cache.put(key, value)
    }

    fun remove(key: K): Boolean {
        return cache.remove(key) != null
    }

    fun size(): Int {
        return cache.size()
    }



    override fun toString(): String {
        return cache.toString()
    }





}