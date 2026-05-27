package ru.practicum.android.projectmonth.shoppinglist.data

import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: Long): Product {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(
        id: Long,
        product: Product
    ): Product {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewProductAndReturnId(product: Product): Long {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewProduct(product: Product): Product {
        TODO("Not yet implemented")
    }
}