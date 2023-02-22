package com.mathias8dev.edt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mathias8dev.edt.ui.screens.colorSelector.ColorSelectorScreen
import com.mathias8dev.edt.ui.screens.edtAdd.EdtAddScreen
import com.mathias8dev.edt.ui.screens.home.HomeScreen
import com.mathias8dev.edt.ui.screens.setting.SettingScreen
import com.mathias8dev.edt.ui.theme.EDTTheme
import com.mathias8dev.edt.ui.screens.task.TaskScreen
import com.mathias8dev.edt.ui.screens.taskAddEdit.TaskAddEditScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EDTTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen() }

                        composable("tasks") { TaskScreen() }

                        composable("settings") { SettingScreen() }

                        composable("edtAddScreen") { EdtAddScreen() }

                        composable("taskAddEdit") { TaskAddEditScreen() }
                        // 1 Samuel 2 V 30
                        // Deuteronome chapitre 10 V 12
                        // Jean 10

                    }*/
                    ColorSelectorScreen()
                }
            }
        }
    }

    /*private fun onNavigateTo(route: Route) {

    }*/
}

