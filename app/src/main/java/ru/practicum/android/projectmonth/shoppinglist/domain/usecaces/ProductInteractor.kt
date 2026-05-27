package ru.practicum.android.projectmonth.shoppinglist.domain.usecaces

import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

interface ProductInteractor {

    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Long): Product
    suspend fun updateProduct(id: Long, product: Product): Product
    suspend fun saveNewProductAndReturnId(product: Product): Long
    suspend fun saveNewProduct(product: Product): Product

}