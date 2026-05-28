package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor

class ProductInteractorImpl(val repository: ProductRepository) : ProductInteractor {
    override fun getAllProducts(): Flow<List<Product>> {
        return repository.getAllProducts()
    }

    override fun getProductById(id: Long): Flow<Product?> {
        return repository.getProductById(id)
    }

    override fun updateProduct(
        id: Long,
        product: Product
    ): Flow<Product> {
        return repository.updateProduct(id, product)
    }

    override fun saveNewProductAndReturnId(product: Product): Flow<Long> {
        return repository.saveNewProductAndReturnId(product)
    }

    override fun saveNewProduct(product: Product): Flow<Product> {
        return repository.saveNewProduct(product)
    }
}
