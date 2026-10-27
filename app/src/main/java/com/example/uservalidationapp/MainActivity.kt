package com.example.uservalidationapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.uservalidationapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. XỬ LÝ NÚT ĐĂNG KÝ
        binding.btnRegister.setOnClickListener {
            val fullName = binding.edtFullName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            // Reset lại lỗi cũ trước khi kiểm tra
            binding.tilFullName.error = null
            binding.tilEmail.error = null
            binding.tilPhone.error = null
            binding.tilPassword.error = null

            var isValid = true
            var errorMessage = ""

            // Validate các trường nhập
            if (fullName.isEmpty()) {
                binding.tilFullName.error = "Họ tên không được để trống"
                errorMessage = "Vui lòng nhập đầy đủ thông tin!"
                isValid = false
            }
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.error = "Email không hợp lệ"
                errorMessage = "Email không hợp lệ!"
                isValid = false
            }
            if (phone.isEmpty() || !phone.matches(Regex("^[0-9]{10}$"))) {
                binding.tilPhone.error = "SĐT phải gồm đúng 10 chữ số"
                errorMessage = "Số điện thoại không hợp lệ!"
                isValid = false
            }
            if (password.isEmpty() || password.length < 6) {
                binding.tilPassword.error = "Mật khẩu phải từ 6 ký tự trở lên"
                errorMessage = "Mật khẩu quá ngắn!"
                isValid = false
            }

            if (!isValid) {
                // FAIL: Hiển thị Snackbar báo lỗi theo yêu cầu giáo viên
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, theme))
                    .setTextColor(resources.getColor(android.R.color.white, theme))
                    .show()
            } else {
                // SUCCESS: Tạo đối tượng User và chuyển sang màn hình Trang chủ
                val user = User(fullName, email, phone, password)

                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("EXTRA_USER", user)
                }
                startActivity(intent)
            }
        }

        // 2. XỬ LÝ NÚT HUỶ (Xoá trắng các trường để nhập lại)
        binding.btnCancel.setOnClickListener {
            binding.edtFullName.text?.clear()
            binding.edtEmail.text?.clear()
            binding.edtPhone.text?.clear()
            binding.edtPassword.text?.clear()

            // Xóa sạch các thông báo lỗi nếu có
            binding.tilFullName.error = null
            binding.tilEmail.error = null
            binding.tilPhone.error = null
            binding.tilPassword.error = null

            Snackbar.make(binding.root, "Đã làm mới form!", Snackbar.LENGTH_SHORT).show()
        }
    }
}