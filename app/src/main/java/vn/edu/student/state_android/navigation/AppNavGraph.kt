package vn.edu.student.state_android.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.student.state_android.ui.screens.SecondScreen
import vn.edu.student.state_android.ui.screens.StateLabScreen

private const val TAG = "STATE_LAB"

object Routes {
    const val STATE_LAB = "state_lab"
    const val SECOND_SCREEN = "second_screen"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.STATE_LAB) {
        composable(Routes.STATE_LAB) {
            StateLabScreen(
                onNavigateToSecondScreen = {
                    Log.d(TAG, "Navigation | StateLabScreen -> SecondScreen")
                    navController.navigate(Routes.SECOND_SCREEN)
                }
            )
        }
        composable(Routes.SECOND_SCREEN) {
            SecondScreen(
                onBack = {
                    Log.d(TAG, "Navigation | SecondScreen -> popBackStack() -> StateLabScreen")
                    navController.popBackStack()
                }
            )
        }
    }
}