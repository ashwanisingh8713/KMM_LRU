package com.compose.imagecache.ui.compose

import io.ktor.http.*

/**
 * Represents the source from where data has been loaded.
 */
public enum class DataSource {

    /**
     * Represents an in-memory data source (e.g. [Cache]).
     */
    Memory,

    /**
     * Represents a disk data source (e.g. [File]).
     */
    Disk,

    /**
     * Represents a network data source (e.g. [Url]).
     */
    Network,

    /**
     * Represents no data source.
     */
    None,
}