package com.assignment.youverifytest.domain.models

import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val images: List<String>,
    val reviews: List<Review>
)

@Serializable
data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

@Serializable
data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
