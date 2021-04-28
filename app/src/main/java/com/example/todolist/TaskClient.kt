package com.example.todolist

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Network class that helps us handle APIs

//const val BASEURL = "https://www.matthewheck.me/to_do_list/tasks/"
const val BASEURL = "http://10.0.2.2/backEnd/tasks/"



class TaskClient {

    companion object {

        private var retrofit:Retrofit?=null

        fun getClient(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }
}
}
