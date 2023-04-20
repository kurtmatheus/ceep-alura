package br.com.alura.ceep.repository

import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.NotaWebClient
import kotlinx.coroutines.flow.first

class NotaRepository(
    private val dao: NotaDao,
    private val client: NotaWebClient
) {

    fun buscaTodas() = dao.buscaTodas()

    suspend fun atualizaTodas() {
        client.buscaTodas()?.let { notas ->
            val notasSincronizadas = notas.map { nota ->
                nota.copy(sincronizada = true)
            }
            dao.salva(notasSincronizadas)
        }
    }

    fun buscaPorId(id: String) = dao.buscaPorId(id)

    suspend fun remove(id: String) {
        dao.remove(id)
        client.remove(id)
    }

    suspend fun salva(nota: Nota) {
        dao.salva(nota)
        if(client.salva(nota)) {
            val notaSincronizada = nota.copy(sincronizada = true)
            dao.salva(notaSincronizada)
        }
    }

    suspend fun sincroniza() {
        val notasNaoSincronizadas = dao.buscaNaoSincronizadas().first()
        notasNaoSincronizadas.forEach { notas ->
            salva(notas)
        }
        atualizaTodas()
    }
}