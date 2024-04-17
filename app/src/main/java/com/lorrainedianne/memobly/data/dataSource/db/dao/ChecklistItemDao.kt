package com.lorrainedianne.memobly.data.dataSource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lorrainedianne.memobly.domain.model.ChecklistItem

@Dao
interface ChecklistItemDao {
    @Query("SELECT * FROM checklist_item_table ORDER BY createdAtTimeStamp ASC")
    fun getChecklistItem(): List<ChecklistItem>

    @Query("SELECT * FROM checklist_item_table WHERE id = :id LIMIT 1")
    fun getChecklistItem(id: String): ChecklistItem

    @Update
    fun update(checklistItem: ChecklistItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(checklistItem: ChecklistItem)

    @Query("DELETE FROM checklist_item_table WHERE id = :id")
    fun delete(id: String)

}