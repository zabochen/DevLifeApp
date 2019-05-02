package ua.ck.zabochen.devlifeapp.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.data.network.AppNetworkImpl
import ua.ck.zabochen.devlifeapp.data.parser.AppParserImpl
import ua.ck.zabochen.devlifeapp.ui.base.BaseActivity
import java.io.IOException

class MainActivity : BaseActivity(), AnkoLogger {

    private lateinit var unbinder: Unbinder

    @BindView(R.id.activityMain_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityMain_bottomNavigation)
    lateinit var bottomNavigation: BottomNavigationView

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        networkRequest(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::unbinder.isInitialized) unbinder.unbind()
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)
        this.unbinder = ButterKnife.bind(this)

        // Toolbar
        setSupportActionBar(toolbar)

        // Navigation
        this.navigationController = Navigation.findNavController(this, R.id.activityMain_fragment_navigationHost)

        // BottomNavigation
        NavigationUI.setupWithNavController(bottomNavigation, navigationController)
    }

    private fun networkRequest(page: Int) = launch {
        try {
            val asyncEntryList = async(Dispatchers.IO) { AppNetworkImpl(AppParserImpl()).getNewEntries(page) }
            info { asyncEntryList.await().joinToString() }
        } catch (e: IOException) {
            info { e.printStackTrace() }
        }
    }
}
