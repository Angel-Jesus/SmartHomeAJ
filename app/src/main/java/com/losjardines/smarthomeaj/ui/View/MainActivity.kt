package com.losjardines.smarthomeaj.ui.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.databinding.ActivityMainBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityViewModel: ActivityViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        binding.btnAccess.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            activityViewModel.singIn(this, email, password)
        }

        activityViewModel.authStateInit.observe(this) { isSuccess ->
            if (isSuccess) {
                // singIn success go to home
                startActivity(Intent(this, HomeActivity::class.java))

            } else {
                // It's incorrect email or password
                Toast.makeText(this, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onStart() {
        super.onStart()

        if (activityViewModel.auth.currentUser != null) {
            // singIn success go to home
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

}