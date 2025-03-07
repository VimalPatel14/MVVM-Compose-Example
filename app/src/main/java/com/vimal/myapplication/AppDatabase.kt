package com.vimal.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vimal.myapplication.db.Converters
import com.vimal.myapplication.db.dao.PostDao
import com.vimal.myapplication.db.dao.UserDao
import com.vimal.myapplication.model.Post
import com.vimal.myapplication.model.user.User

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
}