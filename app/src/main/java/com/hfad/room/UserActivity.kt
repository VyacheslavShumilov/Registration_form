package com.hfad.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hfad.room.dao.UsersDao
import com.hfad.room.databinding.ActivityUserBinding
import com.hfad.room.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var usersDao: UsersDao
    private var id: String = ""


    private lateinit var user: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        id = args?.get("ID").toString() ?: "default"

        usersDao = (application as App).getDatabase().usersDao()

        lifecycleScope.launch(Dispatchers.IO) {
            user = usersDao.getUserId(id.toLong())
            binding.nameTextView.text = user.name
            binding.emailTextView.text = user.email
            binding.phoneTextView.text = user.phone
            binding.idTextView.text = user.id.toString()
        }

        binding.deleteUser.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                usersDao.deleteUser(user)
                finish()
            }
        }

        binding.editDataUser.setOnClickListener {
            val intent = Intent(this, EditUserActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }

    }
}