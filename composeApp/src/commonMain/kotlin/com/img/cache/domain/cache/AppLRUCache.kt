package com.img.cache.domain.cache

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock



class AppLRUCache<K,V>(private val capacity: Int) {

    private val cache: LinkedHashMap<K, V> by lazy {
        object : LinkedHashMap<K, V>(capacity, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
                return size > capacity
            }
        }

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
        return cache[key]
    }

    fun put(key: K, value: V) {
        cache[key] = value
    }

    fun remove(key: K): Boolean {
        return cache.remove(key) != null
    }

    fun size(): Int {
        return cache.size
    }

    fun clear() {
        cache.clear()
    }

    override fun toString(): String {
        return cache.toString()
    }





}