package vn.edu.student.state_android

import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import vn.edu.student.state_android.navigation.AppNavGraph
import vn.edu.student.state_android.ui.theme.StateSurvivalLabTheme

private const val TAG = "STATE_LAB"

class MainActivity : ComponentActivity() {
    private val activityId: Int
        get() = System.identityHashCode(this)

    private val processId: Int
        get() = Process.myPid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(
            TAG,
            "ACTIVITY | onCreate | " +
                    "activityId=$activityId | " +
                    "pid=$processId | " +
                    "hasSavedState=${savedInstanceState != null}"
        )

        setContent {
            StateSurvivalLabTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavGraph()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(
            TAG,
            "ACTIVITY | onStart | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            TAG,
            "ACTIVITY | onResume | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )
    }

    override fun onPause() {

        Log.d(
            TAG,
            "ACTIVITY | onPause | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )

        super.onPause()
    }

    override fun onStop() {

        Log.d(
            TAG,
            "ACTIVITY | onStop | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )

        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(
            TAG,
            "ACTIVITY | onRestart | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {

        Log.d(
            TAG,
            "ACTIVITY | onSaveInstanceState | " +
                    "activityId=$activityId | " +
                    "pid=$processId"
        )

        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d(
            TAG,
            "ACTIVITY | onDestroy | " +
                    "activityId=$activityId | " +
                    "pid=$processId | " +
                    "changingConfigurations=$isChangingConfigurations"
        )

        super.onDestroy()
    }
}