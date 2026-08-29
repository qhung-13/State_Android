package vn.edu.student.state_android.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

private const val TAG = "STATE_LAB"

@Composable
fun DestinationLifecycleLogger(
    screenName: String
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(
        lifecycleOwner,
        screenName
    ) {

        Log.d(
            TAG,
            "DESTINATION | $screenName | observer attached"
        )

        val observer = LifecycleEventObserver { _, event ->

            Log.d(
                TAG,
                "DESTINATION | $screenName | event=$event"
            )
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {

            lifecycleOwner.lifecycle.removeObserver(observer)

            Log.d(
                TAG,
                "DESTINATION | $screenName | observer disposed"
            )
        }
    }
}