package com.example.todoapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity
@Parcelize
data class TaskItem(
    val name: String,
    val isImportant: Boolean = false,
    val isDone: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
): Parcelable {
    val date: String get() = DateFormat.getDateTimeInstance().format(createdAt)
}