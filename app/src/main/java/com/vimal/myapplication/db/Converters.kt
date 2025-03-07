package com.vimal.myapplication.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vimal.myapplication.model.user.Address
import com.vimal.myapplication.model.user.Company

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromAddress(address: Address?): String? {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(addressString: String?): Address? {
        return if (addressString == null) null else gson.fromJson(addressString, object : TypeToken<Address>() {}.type)
    }

    @TypeConverter
    fun fromCompany(company: Company?): String? {
        return gson.toJson(company)
    }

    @TypeConverter
    fun toCompany(companyString: String?): Company? {
        return if (companyString == null) null else gson.fromJson(companyString, object : TypeToken<Company>() {}.type)
    }
}