package br.com.alura.ceep.webclient.service

import br.com.alura.ceep.webclient.form.NotaForm
import br.com.alura.ceep.webclient.model.NotaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotaService {

    @GET("notas")
    suspend fun buscaTodas(): List<NotaModel>

    @PUT("notas/{id}")
    suspend fun salva(@Path("id") id: String, @Body form: NotaForm): Response<NotaModel>

    @DELETE("notas/{id}")
    suspend fun remove(@Path("id") id: String): Response<Void>
}