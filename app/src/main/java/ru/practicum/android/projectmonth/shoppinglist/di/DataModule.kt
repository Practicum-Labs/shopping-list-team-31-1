package ru.practicum.android.projectmonth.shoppinglist.di

import androidx.room.Room
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.projectmonth.shoppinglist.BuildConfig
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ProductDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ShoppingListDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.db.AppDatabase
import ru.practicum.android.projectmonth.shoppinglist.data.network.AuthApiService
import ru.practicum.android.projectmonth.shoppinglist.data.network.HeadersInterceptor

val dataModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
            .build()
    }

    single<AuthApiService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }


    // region Converters
    single {
        ShoppingListDbConverter(get())
    }
    single {
        ProductDbConverter()
    }

    factory {
        Gson()
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}
