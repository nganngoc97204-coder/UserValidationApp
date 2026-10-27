package com.example.uservalidationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uservalidationapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận đối tượng User được truyền từ MainActivity qua Intent
        val user = intent.getSerializableExtra("EXTRA_USER") as? User

        if (user != null) {
            binding.tvWelcome.text = "Xin chào, ${user.fullName}!"
            binding.tvEmail.text = "Email: ${user.email}"
            binding.tvPhone.text = "Số điện thoại: ${user.phone}"
        }

        binding.btnLogout.setOnClickListener {
            finish() // Quay lại màn hình đăng ký
        }
    }
}