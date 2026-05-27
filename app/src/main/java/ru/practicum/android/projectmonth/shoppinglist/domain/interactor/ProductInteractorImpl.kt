package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor

class ProductInteractorImpl(val repository: ProductRepository) : ProductInteractor {
    override suspend fun getAllProducts(): List<Product> {
        return repository.getAllProducts()
    }

    override suspend fun getProductById(id: Long): Product {
        return repository.getProductById(id)
    }

    override suspend fun updateProduct(
        id: Long,
        product: Product
    ): Product {
        return repository.updateProduct(id, product)
    }

    override suspend fun saveNewProductAndReturnId(product: Product): Long {
        return repository.saveNewProductAndReturnId(product)
    }

    override suspend fun saveNewProduct(product: Product): Product {
        return repository.saveNewProduct(product)
    }
}