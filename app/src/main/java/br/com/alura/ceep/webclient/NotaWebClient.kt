package br.com.alura.ceep.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.form.NotaForm

const val TAG = "NotaWebCLient"

class NotaWebClient {
    private val notaService = RetrofitInicializador().notaService

    suspend fun buscaTodas(): List<Nota>? {
        return try {
            val listaNotas = notaService.buscaTodas()
            listaNotas.map { it.nota }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun salva(nota: Nota): Boolean {
        try {
            val resposta = notaService.salva(
                nota.id, NotaForm(
                    titulo = nota.titulo,
                    descricao = nota.descricao,
                    imagem = nota.imagem
                )
            )
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.i(TAG, "atualiza: falha ao tentar salvar")
        }
        return false
    }

    suspend fun remove(id: String) : Boolean {
        try {
            val resposta = notaService.remove(id)
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.i(TAG, "atualiza: falha ao tentar remover")
        }
        return false
    }
}