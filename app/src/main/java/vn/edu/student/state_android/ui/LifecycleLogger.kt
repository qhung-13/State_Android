package vn.edu.student.state_android.ui
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun LifecycleLogger() {
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                // Nhóm yêu cầu dùng tag STATE_LAB thống nhất
                Lifecycle.Event.ON_CREATE -> Log.d("STATE_LAB", "MainActivity onCreate")
                Lifecycle.Event.ON_START -> Log.d("STATE_LAB", "MainActivity onStart")
                Lifecycle.Event.ON_RESUME -> Log.d("STATE_LAB", "MainActivity onResume")
                Lifecycle.Event.ON_PAUSE -> Log.d("STATE_LAB", "MainActivity onPause")
                Lifecycle.Event.ON_STOP -> Log.d("STATE_LAB", "MainActivity onStop")
                Lifecycle.Event.ON_DESTROY -> Log.d("STATE_LAB", "MainActivity onDestroy")
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
