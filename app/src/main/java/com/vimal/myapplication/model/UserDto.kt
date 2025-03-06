 package com.vimal.myapplication.model

 import com.vimal.myapplication.model.user.Address
 import com.vimal.myapplication.model.user.Company
 import com.vimal.myapplication.model.user.User

 data class UserDto(
     val address: Address,
     val company: Company,
     val email: String,
     val id: Int,
     val name: String,
     val phone: String,
     val username: String,
     val website: String
) {
    fun toDomain(): User {
        return User(address, company, email, name, phone, username, website)
    }
}