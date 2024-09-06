package com.smtersoyoglu.kekodproject.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smtersoyoglu.kekodproject.viewmodel.DashboardViewModel
import com.smtersoyoglu.kekodproject.R
import com.smtersoyoglu.kekodproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        observeViewModel()
        setupBottomNavigation()


    }

    private fun setupNavigation() {
        // Navigation controller'ı initialize et
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun observeViewModel() {
        // BottomNavigationBar görünürlüğünü ViewModel'den observe et
        viewModel.isBottomNavVisible.observe(this) { isVisible ->
            binding.bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

        // BottomNavigationBar item'larını dinamik olarak ViewModel'den observe et
        viewModel.menuItems.observe(this) { items ->
            val menu = binding.bottomNav.menu
            menu.clear()
            menuInflater.inflate(R.menu.bottom_nav_menu, menu) // XML'den Home item'ını ekler

            items.forEach { item ->
                val menuItem = menu.add(Menu.NONE, item.id, Menu.NONE, item.title)
                menuItem.setIcon(item.iconResId)
                menuItem.title = item.title
                menuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM or MenuItem.SHOW_AS_ACTION_WITH_TEXT)
            }

            // Varsayılan olarak Home item'ını seçili hale getir
            menu.findItem(R.id.dashboardFragment)?.isChecked = true
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            val destinationId = when (item.itemId) {
                R.id.dashboardFragment -> R.id.dashboardFragment
                R.id.happinessSwitch -> R.id.happinessFragment
                R.id.optimismSwitch -> R.id.optimismFragment
                R.id.kindnessSwitch -> R.id.kindnessFragment
                R.id.givingSwitch -> R.id.givingFragment
                R.id.respectSwitch -> R.id.respectFragment
                else -> null
            }
            destinationId?.let { id ->
                navController.navigate(id)
            }
            true
        }

    }

}