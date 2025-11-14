package com.example.mini2.data

import com.example.mini2.data.local.UserEntity
import com.example.mini2.data.remote.ApiUser

fun ApiUser.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}
