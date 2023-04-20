package br.com.alura.ceep.webclient

import br.com.alura.ceep.webclient.service.NotaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInicializador {

    private val client by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.20.1.185:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val notaService: NotaService = retrofit.create(NotaService::class.java)
}