package com.hfad.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hfad.room.model.Users

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<Users>

    @Query("SELECT * FROM users WHERE id= :id")
    fun getUserId(id: Long): Users

    @Insert
    fun insertUsers(users: Users)

    @Delete
    fun deleteUser(users: Users)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("UPDATE users SET name = :name, email = :email, phone =:phone WHERE id =:id")
    fun editDataUser(id: Long, name:String, email:String, phone: String)
}