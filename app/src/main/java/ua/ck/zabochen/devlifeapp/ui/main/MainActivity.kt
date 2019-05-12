package ua.ck.zabochen.devlifeapp.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.ui.base.BaseActivity

// TODO: AnkoLogger
class MainActivity : BaseActivity(), AnkoLogger {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)

        // Toolbar
        setSupportActionBar(activityMain_toolbar)

        // Navigation
        this.navigationController = Navigation.findNavController(this, R.id.activityMain_fragment_navigationHost)

        // BottomNavigation
        NavigationUI.setupWithNavController(activityMain_bottomNavigation, navigationController)
    }
}
