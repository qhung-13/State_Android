package vn.edu.student.state_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.student.state_android.ui.screens.SecondScreen
import vn.edu.student.state_android.ui.screens.StateLabScreen

private object Routes {
    const val STATE_LAB = "state_lab"
    const val SECOND_SCREEN = "second_screen"
}

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.STATE_LAB
    ) {

        composable(
            route = Routes.STATE_LAB
        ) {

            StateLabScreen(
                onGoToSecondScreen = {
                    navController.navigate(
                        Routes.SECOND_SCREEN
                    )
                }
            )
        }

        composable(
            route = Routes.SECOND_SCREEN
        ) {

            SecondScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}