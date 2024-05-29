package com.lorrainedianne.memobly.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

open class Base(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    var createdAtTimeStamp: Long = System.currentTimeMillis() / 1000,
    var updatedAtTimeStamp: Long = -1,
    var deletedAtTimeStamp: Long = -1
)
