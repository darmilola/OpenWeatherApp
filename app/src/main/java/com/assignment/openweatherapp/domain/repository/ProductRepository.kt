package com.assignment.youverifytest.domain.shop

import com.assignment.youverifytest.domain.models.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

open class ProductRepository(private val apiService: HttpClient) {

    companion object {
        private const val BASE_URL = "https://dummyjson.com/products"
    }

    suspend fun getProducts(page: Int, pageSize: Int = 10): ProductResponse {
            val skip = (page - 1) * pageSize
            return apiService.get("$BASE_URL?limit=$pageSize&skip=$skip").body()
        }


}