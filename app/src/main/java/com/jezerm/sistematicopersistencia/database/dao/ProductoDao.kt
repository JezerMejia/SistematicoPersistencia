package com.jezerm.sistematicopersistencia.database.dao

import androidx.room.*
import com.jezerm.sistematicopersistencia.database.entities.EntityProducto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Query("SELECT * FROM TblProducto")
    fun obtRegistros(): Flow<List<EntityProducto>>

    @Query("SELECT * FROM TblProducto WHERE idProd = :id")
    fun obtRegistro(id: Int): Flow<EntityProducto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarReg(producto: EntityProducto)

    @Update
    suspend fun actualizarReg(producto: EntityProducto)

    @Delete
    suspend fun eliminarReg(producto: EntityProducto)

    @Query("DELETE FROM TblProducto WHERE idProd = :id")
    suspend fun eliminarReg(id: Int)
}