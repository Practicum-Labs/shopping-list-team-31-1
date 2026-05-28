package ru.practicum.android.projectmonth.shoppinglist.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: Long): Flow<Product?>
    fun updateProduct(id: Long, product: Product): Flow<Product>
    fun saveNewProductAndReturnId(product: Product): Flow<Long>
    fun saveNewProduct(product: Product): Flow<Product>

}
