package com.example.feature_weatherinformation.data.util

import java.lang.reflect.Type

interface JsonnParser {

    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(obj: T, type: Type): String?
}