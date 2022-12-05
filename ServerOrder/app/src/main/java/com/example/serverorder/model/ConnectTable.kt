package com.example.serverorder.model

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class ConnectTable(
    @Embedded
    val order: Order?,
    @Relation(
        parentColumn = "col_idCustomer",
        entityColumn = "col_id"
    )
    val customer: Customer?
) : Serializable
