package com.grifalion.contactspro.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Contact(

    val id: Int? = null,

    val imIcon: String?,

    val firstName: String ,

    val lastName: String,

    val numberPhone: String

)
