package com.hfad.room.users

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hfad.room.App
import com.hfad.room.dao.UsersDao
import com.hfad.room.databinding.ActivityUsersBinding
import com.hfad.room.model.Users
import com.hfad.room.user.UserActivity
import com.hfad.room.users.adapter.AdapterUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersActivity : AppCompatActivity(), AdapterUsers.SetOnClickListener {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var usersDao: UsersDao
    private lateinit var adapterUsers: AdapterUsers
    private var users: ArrayList<Users> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersDao = (application as App).getDatabase().usersDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val usersList = usersDao.getAllUsers() as ArrayList
            users.addAll(usersList)

        }
        adapterUsers = AdapterUsers(users, this)
        binding.recyclerViewUsers.adapter = adapterUsers

        binding.deleteAllUsers.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                //usersDao
            }
            users.clear()
            adapterUsers.notifyDataSetChanged()
        }
    }

    @SuppressLint
    override fun onClickDeleteUser(users: Users) {
        lifecycleScope.launch(Dispatchers.IO){
            usersDao.deleteUser(users)
        }
    }

    override fun onClickUser(id: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("ID", id.toString())
        startActivity(intent)
    }

}