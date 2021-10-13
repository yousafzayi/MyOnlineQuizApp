package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class UserActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
   private lateinit var navigationView: NavigationView
   private lateinit var bottomNav: BottomNavigationView
   private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)






       // bottomNav = bottom_navigation
        bottomNav = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.hostFragment)
        setupBottomNavigation()

        //drawerLayout = drawer_layout
        drawerLayout =findViewById(R.id.drawer_layout)

        // For Navigation UP button Arrow
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //NavigationUI.setupWithNavController(navigation_view,navController)
        navigationView = findViewById(R.id.navigation_view)
        NavigationUI.setupWithNavController(navigationView, navController)

    }


    override fun onSupportNavigateUp(): Boolean {

        //it will navigate  you to the exact location
        return NavigationUI.navigateUp(navController, appBarConfiguration)

    }


    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

}