package com.jezerm.sistematicopersistencia.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TblProducto")
data class EntityProducto(
    @ColumnInfo("idProd")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("nombreProd")
    val nombre: String,
    @ColumnInfo("precioProd")
    val precio: Double,
    @ColumnInfo("existProd")
    val existencia: Boolean
)

