package ru.practicum.android.projectmonth.shoppinglist.domain.usecaces

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

interface ProductInteractor {

    fun getAllProducts(): Flow<List<Product>>
    fun updateProduct(id: Long, product: Product): Flow<Product>
    fun saveNewProduct(product: Product): Flow<Product>

    fun getProductsByShoppingListId(id: Long): Flow<List<Product>>
    suspend fun removeProduct(productId: Long)
    suspend fun deleteShoppingListProducts(shoppingListId: Long)
}
