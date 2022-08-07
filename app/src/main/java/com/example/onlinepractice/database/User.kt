package com.example.onlinepractice.database

import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
)

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT password FROM user where email == :email")
    fun getUserPasswordByEmail(email: String): String

    @Insert
    fun insertUser(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class UserDatabase(): RoomDatabase(){
    abstract fun userDao(): UserDao
}