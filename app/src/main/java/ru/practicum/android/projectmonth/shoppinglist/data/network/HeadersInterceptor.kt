package ru.practicum.android.projectmonth.shoppinglist.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authenticatedRequest = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
