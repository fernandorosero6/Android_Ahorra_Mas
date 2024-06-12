package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ahorra.databinding.ActivityInicioBinding
import com.google.android.material.navigation.NavigationView

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Set up the drawer layout and toggle
        drawerLayout = binding.drawerLayout
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation item clicks
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_perfil -> {
                    // Handle the profile action (if any)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_inicio -> {
                    // Handle the home action
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_contadores -> {
                    // Handle the counters action
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_historial -> {
                    // Handle the history action
                    val intent = Intent(this, HistorialActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_presupuesto -> {
                    // Handle the budget action (if any)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_danios -> {
                    // Handle the damages action (if any)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_acerca_de -> {
                    // Handle the about us action (if any)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_cerrar_sesion -> {
                    // Handle the logout action (if any)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        // Default selection
        binding.navView.setCheckedItem(R.id.nav_inicio)

        // Example button click listener (replace with your logic)
        binding.BtnHistorialInicio.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}