package com.express.android.briancontactlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo (
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "todo_task") val todoTask: String
   )