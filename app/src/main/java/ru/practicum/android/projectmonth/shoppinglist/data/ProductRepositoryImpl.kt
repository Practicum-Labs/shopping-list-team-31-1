package ru.practicum.android.projectmonth.shoppinglist.data

import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ProductDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.db.AppDatabase
import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

class ProductRepositoryImpl(
    val appDatabase: AppDatabase,
    val productDbConverter: ProductDbConverter,
) : ProductRepository {

    override suspend fun getAllProducts(login: String): List<Product> {
        return appDatabase.productDao().getAll(login).map { productDbConverter.map(it) }
    }

    override suspend fun getProductById(id: Long): Product {
        return productDbConverter.map(appDatabase.productDao().getById(id))
    }

    override suspend fun updateProduct(id: Long, product: Product): Product {
        appDatabase.productDao().update(productDbConverter.map(product))
        return productDbConverter.map(appDatabase.productDao().getById(product.id))
    }

    override suspend fun saveNewProductAndReturnId(product: Product): Long {
        return appDatabase.productDao().insert(productDbConverter.map(product))
    }

    override suspend fun saveNewProduct(product: Product): Product {
        val id = appDatabase.productDao().insert(productDbConverter.map(product))
        return productDbConverter.map(appDatabase.productDao().getById(id))
    }

    override suspend fun getProductsByShoppingListId(id: Long): List<Product> {
        return appDatabase
            .productDao()
            .getProductsByShoppingListId(id)
            .map {productDbConverter.map(it)}
    }

    override suspend fun deleteProduct(productId: Long) {
        appDatabase
            .productDao()
            .deleteById(productId)
    }
}
