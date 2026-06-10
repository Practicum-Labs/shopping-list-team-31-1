package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.projectmonth.shoppinglist.domain.AuthRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor

class ProductInteractorImpl(
    val repository: ProductRepository,
    val authRepository: AuthRepository
) : ProductInteractor {
    override fun getAllProducts(): Flow<List<Product>> = flow {
        emit (repository.getAllProducts(authRepository.currentUser()))
    }
        .flowOn(Dispatchers.IO)

    override fun updateProduct(
        id: Long,
        product: Product
    ): Flow<Product> = flow {
        emit(repository.updateProduct(id, product))
    }
        .flowOn(Dispatchers.IO)

    override fun saveNewProduct(product: Product): Flow<Product> = flow {
        emit(repository.saveNewProduct(product))
    }
        .flowOn(Dispatchers.IO)

    override fun getProductsByShoppingListId(id: Long): Flow<List<Product>> = flow {
        emit(repository.getProductsByShoppingListId(id))
    }
        .flowOn(Dispatchers.IO)

    override suspend fun removeProduct(productId: Long) {
        repository.deleteProduct(productId)
    }
}
