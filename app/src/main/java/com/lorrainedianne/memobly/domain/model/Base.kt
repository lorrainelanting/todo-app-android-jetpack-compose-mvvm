package com.lorrainedianne.memobly.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

open class Base(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") var id: String = "",
    var createdAtTimeStamp: Long = System.currentTimeMillis() / 1000,
    var updatedAtTimeStamp: Long = -1,
    var deletedAtTimeStamp: Long = -1
)
