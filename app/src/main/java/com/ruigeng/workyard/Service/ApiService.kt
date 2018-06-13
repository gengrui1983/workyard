package com.ruigeng.workyard.Service

import android.arch.lifecycle.LiveData
import com.ruigeng.workyard.data.ItemListResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


/**
 * Created by rui.geng on 12/10/17.
 */
interface ApiService {

    @GET("/public_feed")
    fun getItems(): Observable<ItemListResponse>

    companion object Factory {

        fun create(): ApiService {

            val client = OkHttpClient.Builder().addInterceptor({ chain ->
                val newRequest = chain.request().newBuilder()
                        .build()

                chain.proceed(newRequest)
            })
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(Api.END_POINT)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}