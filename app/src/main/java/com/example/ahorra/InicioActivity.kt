package com.example.ahorra

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.ahorra.acercaDeNosotros.AcercaDeNosotros
import com.example.ahorra.contador.ContadorFragment
import com.example.ahorra.databinding.ActivityInicioBinding
import com.example.ahorra.historial.HistorialFragment
import com.example.ahorra.presupuesto.PresupuestoFragment
import com.example.ahorra.reportes.Reporte_De_Danos
import com.example.ahorra.ui.home.HomeFragment
import com.example.myapp.perfil.PerfilFragment

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var btnContadores: Button
    private lateinit var btnDanos: Button
    private lateinit var btnPresupuesto: Button
    private lateinit var btnHistorialInicio: Button

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

        // Check if we're recreating a previously destroyed instance
        if (savedInstanceState == null) {
            // Load the HomeFragment by default when the activity is first created
            replaceFragment(HomeFragment())
        }

        // Handle navigation item clicks
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_perfil -> {
                    replaceFragment(PerfilFragment())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_contadores -> {
                    replaceFragment(ContadorFragment())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_historial -> {
                    replaceFragment(HistorialFragment())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_presupuesto -> {
                    replaceFragment(PresupuestoFragment())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_danios -> {
                    replaceFragment(Reporte_De_Danos())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_acerca_de -> {
                    replaceFragment(AcercaDeNosotros())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_cerrar_sesion -> {

                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Opcional: añade a la pila de retroceso
            .commit()
    }
}
