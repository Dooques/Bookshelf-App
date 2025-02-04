@file:OptIn(ExperimentalSerializationApi::class)

package com.example.androidcourse_18_bookshelfapp.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys


class Bookshelf {

    @Serializable
    @JsonIgnoreUnknownKeys
    data class BookList(
        val totalItems: Int,
        val items: List<VolumeId>
    )

    @Serializable
    @JsonIgnoreUnknownKeys
    data class VolumeId(
        val id: String
    )

    @Serializable
    @JsonIgnoreUnknownKeys
    data class Volume(
        val id: String = "",
        val volumeInfo: VolumeInfo = VolumeInfo(),
        val saleInfo: SaleInfo = SaleInfo()
    )

    @Serializable
    @JsonIgnoreUnknownKeys
    data class SaleInfo(
        val country: String = "",
        val saleability: String = "",
        val isEbook: Boolean = true,
        val retailPrice: RetailPrice? = null,
        val buyLink: String? = null,
    )

    @Serializable
    data class RetailPrice(
        val amount: Double,
        val currencyCode: String
    )

    @Serializable

    data class Identifier(
        val type: String,
        val identifier: String
    )

    @Serializable
    @JsonIgnoreUnknownKeys
    data class ImageLink(
        var thumbnail: String = "",
        var small: String = "",
        var medium: String = "",
        var large: String = ""
    )


    @Serializable
    @JsonIgnoreUnknownKeys
    data class VolumeInfo(
        val title: String = "",
        val authors: List<String> = listOf(),
        val publisher: String = "",
        val publishedDate: String = "",
        val description: String = "",
        val industryIdentifiers: List<Identifier> = listOf(),
        val pageCount: Int = 0,
        val categories: List<String> = listOf(),
        val imageLinks: ImageLink = ImageLink(""),
        val language: String = "",
    )
}