package com.example.rickandmortyapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.rickandmortyapplication.data.remote.dto.Location

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("character_id")
    val characterId: Int?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("status")
    val status: String?,
    @ColumnInfo("location")
    val location: Location?,
    @ColumnInfo("gender")
    val gender: String?,
)

object LocationConverter {
    @TypeConverter
    fun locationToString(location: Location?) : String? {
        return location?.name
    }

    @TypeConverter
    fun stringToLocation(string : String) : Location {
        return Location(string,"")
    }

}
