package com.img.cache.data.repo

import androidx.compose.ui.graphics.ImageBitmap
import com.img.cache.domain.cache.AppLRUCache
import com.img.cache.domain.repo.My_ICacheData
import com.img.decoder.ImageBitmapDecoder
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.HttpMethod

class My_CacheDataImp(
    private val client: HttpClient,
    private val lruCache: AppLRUCache<String, ImageBitmap>
): My_ICacheData {

    override suspend fun getImageFromCache(url: String): ImageBitmap? {
        val ib = lruCache.get(url)
        if(ib != null) {
            return ib
        } else {
            val response = client.request {
                onDownload { bytesSentTotal, contentLength ->
                    val progress = contentLength?.let {
                        (bytesSentTotal.toFloat() / contentLength).coerceIn(0F..1F).takeUnless { it.isNaN() }
                    }

                }
                takeFrom(HttpRequestBuilder().apply { method = HttpMethod.Get })
                url(url)
            }
            val bytes = response.bodyAsChannel()

            try {
                val bitmap = ImageBitmapDecoder.decode(bytes)
                lruCache.put(url, bitmap)
                return bitmap
            } catch (t: Throwable) {
                throw throw IllegalArgumentException("Failed to decode ${url} bytes to a bitmap.")
            }

        }
    }


}