package com.example.feature_weatherinformation.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.feature_weatherinformation.data.util.JsonnParser
import com.example.feature_weatherinformation.domain.model.Temp
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonnParser) {

    @TypeConverter
    fun fromTempJson(json: String): Temp? {
        return jsonParser.fromJson(json, object: TypeToken<Temp>(){}.type)
    }

    @TypeConverter
    fun toTempJson(temp: Temp): String? {
        return jsonParser.toJson(temp, object: TypeToken<Temp>(){}.type)
    }
}