package com.hfad.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.hfad.room.dao.UsersDao
import com.hfad.room.databinding.ActivityMainBinding
import com.hfad.room.model.Users
import com.hfad.room.users.UsersActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var usersDao: UsersDao
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersDao = (application as App).getDatabase().usersDao()

        binding.saveBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                if(isInvalid()) {
                    val users = Users(
                        0,
                        binding.nameEditTxt.text.toString(),
                        binding.emailEditTxt.text.toString(),
                        binding.phoneEditTxt.text.toString()
                    )
                    usersDao.insertUsers(users)
                    Log.e("SUCCESS","OK")
                }
            }
        }

        binding.showAllUserBtn.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
            }
        }

    private fun isInvalid(): Boolean {

        if (binding.nameEditTxt.text?.trim().toString().isEmpty()) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.emailEditTxt.text!!.trim().toString().isEmpty()) {
            Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.phoneEditTxt.text!!.trim().toString().isEmpty()) {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}