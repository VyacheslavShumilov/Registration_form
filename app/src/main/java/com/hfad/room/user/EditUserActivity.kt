package com.hfad.room.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hfad.room.App
import com.hfad.room.dao.UsersDao
import com.hfad.room.databinding.ActivityEditUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    private lateinit var usersDao: UsersDao
    private lateinit var binding: ActivityEditUserBinding
    private var id:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        id = args?.get("ID").toString()?: "default"

        usersDao = (application as App).getDatabase().usersDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val user = usersDao.getUserId(id.toLong())
            binding.editName.setText(user.name)
            binding.editEmail.setText(user.email)
            binding.editPhone.setText(user.phone)
        }

        binding.saveData.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                usersDao.editDataUser(
                    id.toLong(),
                    binding.editName.text.toString(),
                    binding.editEmail.text.toString(),
                    binding.editPhone.text.toString())
            }
            finish()
        }
    }
}