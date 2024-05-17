package ru.mypet.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.LocalDateTime


class LocalDateAdapter : TypeAdapter<LocalDate?>() {
    override fun write(jsonWriter: JsonWriter?, value: LocalDate?) {
        if (value == null) {
            jsonWriter?.nullValue()
        } else {
            jsonWriter?.value(value.format(PetDateTimeFormatter.date))
        }
    }

    override fun read(jsonReader: JsonReader): LocalDate? {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        } else {
            return LocalDate.parse(jsonReader.nextString())
        }
    }
}

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime?>() {
    override fun write(jsonWriter: JsonWriter?, value: LocalDateTime?) {
        if (value == null) {
            jsonWriter?.nullValue()
        } else {
            jsonWriter?.value(value.format(PetDateTimeFormatter.date))
        }
    }

    override fun read(jsonReader: JsonReader): LocalDateTime? {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        } else {
            return LocalDateTime.parse(jsonReader.nextString())
        }
    }
}
