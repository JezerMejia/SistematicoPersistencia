package com.jezerm.sistematicopersistencia.database

import androidx.annotation.WorkerThread
import com.jezerm.sistematicopersistencia.database.dao.ProductoDao
import com.jezerm.sistematicopersistencia.database.entities.EntityProducto
import kotlinx.coroutines.flow.Flow

class Repositorio private constructor(private val dao: ProductoDao) {

    val productos: Flow<List<EntityProducto>> = dao.obtRegistros()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(producto: EntityProducto) {
        dao.insertarReg(producto)
    }

    companion object {
        private var INSTANCE: Repositorio? = null

        fun init(dao: ProductoDao): Repositorio {
            if (INSTANCE != null) return INSTANCE!!
            INSTANCE = Repositorio(dao)
            return INSTANCE!!
        }

        fun getInstance(): Repositorio {
            return INSTANCE!!
        }
    }
}
