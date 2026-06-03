package ru.practicum.android.diploma.data.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.projectmonth.shoppinglist.BuildConfig
import ru.practicum.android.projectmonth.shoppinglist.data.network.AuthApiService
import ru.practicum.android.projectmonth.shoppinglist.data.network.HeadersInterceptor
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.LoginRequest

@RunWith(AndroidJUnit4::class)
class RetrofitNetworkClientRealApiTest {

    private lateinit var apiService: AuthApiService

    @Before
    fun setUp() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(AuthApiService::class.java)

//        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
    }

    @Test
    fun loginTest() = runBlocking {
        val request = LoginRequest(
            email = "dchechumaev@mail.ru",
            password = "dimaMosch"
        )
        val response = apiService.loginUser(request)

        val loginResp = response.body()
        assertNotNull(response)

        assertTrue(
            "Response code should be 200, but was ${response.code()}",
            response.code() == 200
        )
        println("auth response: ${loginResp}")
        println("userId: ${loginResp?.userid}")
        println("access_token: ${loginResp?.accessToken}")
        println("refresh_token: ${loginResp?.refreshToken}")

        val bearerToken = "Bearer ${loginResp?.accessToken}"

        val checkAuthResponse = apiService.checkAuthorization(bearerToken).body()
        println("check authorization: $checkAuthResponse")

    }
    @Test
    fun loginTestBadRequest() = runBlocking {
        val request = LoginRequest(
            email = "dchechumaev@mail.ru",
            password = "dfdd"
        )
        val response = apiService.loginUser(request)
        println("Unsuccess authorization: code=${response.code()}, message=${response.errorBody()?.string()}")

    }
    @Test
    fun loginTestUnauthorized() = runBlocking {
        val request = LoginRequest(
            email = "dchechumaev@mail.ru",
            password = "dfddddRrrr"
        )

        val response = apiService.loginUser(request)
        println("Unsuccess authorization: code=${response.code()}, message=${response.errorBody()?.string()}")

    }
}
