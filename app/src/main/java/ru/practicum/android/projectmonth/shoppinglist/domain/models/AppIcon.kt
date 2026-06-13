package ru.practicum.android.projectmonth.shoppinglist.domain.models

import androidx.annotation.DrawableRes
import ru.practicum.android.projectmonth.shoppinglist.R

enum class AppIcon(val dbKey: String, @DrawableRes val resId: Int) {
    DEFAULT("ic_default", R.drawable.ic_default),
    PRODUCTS("ic_products", R.drawable.ic_products),
    PURCHASES("ic_purchases", R.drawable.ic_purchases),
    FLOWERS("ic_flowers", R.drawable.ic_flowers),
    INSTRUMENT("ic_instrument",R.drawable.ic_instrument),
    FIESTA("ic_fiesta", R.drawable.ic_fiesta),
    CAKE("ic_cake", R.drawable.ic_cake),
    PRESENT("ic_present", R.drawable.ic_present),
    ALCOHOL("ic_alcohol", R.drawable.ic_alcohol),
    ANIMALS("ic_animals", R.drawable.ic_animals),
    PICNIC("ic_picnic", R.drawable.ic_picnic),
    MEDICINE("ic_medicine", R.drawable.ic_medicine),
    LEARNING("ic_learning", R.drawable.ic_learning),
    SPORTS("ic_sports", R.drawable.ic_sports),
    HOME("ic_home", R.drawable.ic_home),
    GYMNASIUM("ic_gymnasium", R.drawable.ic_gymnasium),
    CHILDREN("ic_children", R.drawable.ic_children),
    GAME("ic_game", R.drawable.ic_game),
    DRAWING("ic_drawing", R.drawable.ic_drawing),
    CLOTHING("ic_clothing", R.drawable.ic_clothing),
    CAR("ic_car", R.drawable.ic_car),
    TRAVEL("ic_travel", R.drawable.ic_travel),
    MEDICINES("ic_medicines", R.drawable.ic_medicines),
    COSMETICS("ic_cosmetics", R.drawable.ic_cosmetics),
    PHOTOS("ic_photos", R.drawable.ic_photos),
    BOOKS("ic_books", R.drawable.ic_books),
    YOGA("ic_yoga", R.drawable.ic_yoga),
    PUZZLE("ic_puzzle", R.drawable.ic_puzzle),
    COMPUTER("ic_computer", R.drawable.ic_computer),
    STROLLER("ic_stroller", R.drawable.ic_stroller);

    companion object {
        private val mapByDbKey = entries.associateBy { it.dbKey }

        fun fromDbKey(key: String): AppIcon = mapByDbKey[key] ?: DEFAULT

        @DrawableRes
        fun getResIdByDbKey(key: String): Int = fromDbKey(key).resId
    }
}