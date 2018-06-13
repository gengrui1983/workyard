package com.ruigeng.workyard.data

import com.squareup.moshi.Json

data class ItemListResponse @JvmOverloads constructor(
        @Json(name = "total_count")
        var totalCount: Int = 0,

        var items: List<Item> = arrayListOf()
)

data class Item @JvmOverloads constructor(
        var id: Long = 0L,
        var name: String = "Default Name",
        var suburb: String = "",
        var state: String = "",
        @Json(name = "location_lat")
        var locationLat: Float = 0.0F,
        @Json(name = "location_long")
        var locationLng: Float = 0.0F,
        var posts: List<Post> = arrayListOf(),
        @Json(name = "like_count")
        var likeCount: Int = 0,
        @Json(name = "view_count")
        var viewCount: Int = 0,
        @Json(name = "photo_url")
        var photoUrl: String = "",
        @Json(name = "default_image_url_optimised")
        var defaultImageUrlOptimised: String = ""
)

data class Post @JvmOverloads constructor(
        var id: String = "",
        @Json(name = "default_image_url")
        var defaultImageUrl: String = ""
)