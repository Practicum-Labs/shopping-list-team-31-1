package ru.practicum.android.projectmonth.shoppinglist.data.converter.db

import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductEntity
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

class ProductDbConverter {
    fun map(productEntity: ProductEntity?) : Product? {
        if (productEntity == null) return null
        return Product(
            id = productEntity.id,
            name = productEntity.name,
            checked = productEntity.checked,
            number = productEntity.number,
            measureUnit = productEntity.measureUnit,
            shoppingListId = productEntity.shoppingListId
        )
    }

    fun map(product: Product) : ProductEntity {
        return ProductEntity(
            id = product.id,
            name = product.name,
            checked = product.checked,
            number = product.number,
            measureUnit = product.measureUnit,
            shoppingListId = product.shoppingListId
        )
    }

}
