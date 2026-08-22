package vn.edu.student.state_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.student.state_android.ui.screens.SecondScreen
import vn.edu.student.state_android.ui.screens.StateLabScreen

object Routes {
    const val STATE_LAB = "state_lab"
    const val SECOND_SCREEN = "second_screen"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.STATE_LAB) {
        composable(Routes.STATE_LAB) {
            // A ViewModel created here via viewModel() is scoped to THIS NavBackStackEntry.
            // As long as "state_lab" stays in the back stack, the same ViewModel instance
            // survives navigating to SecondScreen and back. This is the scope you must
            // name explicitly in the Navigation test report (design doc, mục 30).
            StateLabScreen(
                onNavigateToSecondScreen = { navController.navigate(Routes.SECOND_SCREEN) }
            )
        }
        composable(Routes.SECOND_SCREEN) {
            SecondScreen(onBack = { navController.popBackStack() })
        }
    }
}