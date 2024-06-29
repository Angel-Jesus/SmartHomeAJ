package com.losjardines.smarthomeaj.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.databinding.ActivityHomeBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomView : BottomNavigationView
    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        bottomView = findViewById(R.id.bottomNavigationView)

        bottomView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_item -> replaceFragment(HomeFragment())
                R.id.light_item -> replaceFragment(LightFragment())
                R.id.door_item -> replaceFragment(DoorFragment())
                R.id.setting_item -> dialogViewLogOut()
            }
            true
        }
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit()
    }

    private fun dialogViewLogOut()
    {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("¿Desea cerrar sesión?")
        alertDialog.setTitle("Log Out")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Si"){_,_ ->
            signOut()
        }
        alertDialog.setNegativeButton("No"){dialog,_ ->
            dialog.dismiss()
        }

        alertDialog.create().show()

    }

    private fun signOut()
    {
        activityViewModel.singOut(activityViewModel.auth)
        startActivity(Intent(this, MainActivity::class.java))
    }

}