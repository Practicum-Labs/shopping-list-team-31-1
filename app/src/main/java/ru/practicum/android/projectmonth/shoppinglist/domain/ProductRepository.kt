package ru.practicum.android.projectmonth.shoppinglist.domain

import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

interface ProductRepository {

    suspend fun getAllProducts(login: String): List<Product>
    suspend fun getProductById(id: Long): Product
    suspend fun updateProduct(id: Long, product: Product): Product
    suspend fun saveNewProductAndReturnId(product: Product): Long
    suspend fun saveNewProduct(product: Product): Product

    suspend fun getProductsByShoppingListId(id: Long): List<Product>
    suspend fun deleteProduct(productId: Long)
}
