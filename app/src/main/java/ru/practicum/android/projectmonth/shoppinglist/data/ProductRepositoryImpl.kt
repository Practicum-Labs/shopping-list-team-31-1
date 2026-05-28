package ru.practicum.android.projectmonth.shoppinglist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ProductDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.db.AppDatabase
import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

class ProductRepositoryImpl(
    val appDatabase: AppDatabase,
    val productDbConverter: ProductDbConverter
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> =
        appDatabase.productDao().getAll()
            .map { entities -> entities.mapNotNull { productDbConverter.map(it) } }


    override fun getProductById(id: Long): Flow<Product?> = flow {
        emitAll(appDatabase.productDao().getById(id).map { productDbConverter.map(it) })
    }

    override fun updateProduct(
        id: Long,
        product: Product
    ): Flow<Product> = flow {
        appDatabase.productDao().update(productDbConverter.map(product))
        emit(
            appDatabase.productDao().getById(product.id)
            .mapNotNull { productDbConverter.map(it) }
            .first())
    }


    override fun saveNewProductAndReturnId(product: Product): Flow<Long> = flow {
        emit(appDatabase.productDao().insert(productDbConverter.map(product)))
    }

    override fun saveNewProduct(product: Product): Flow<Product> = flow {
        val id = appDatabase.productDao().insert(productDbConverter.map(product))
        emitAll(
            appDatabase.productDao().getById(id)
                .mapNotNull { productDbConverter.map(it) }
        )
    }

}