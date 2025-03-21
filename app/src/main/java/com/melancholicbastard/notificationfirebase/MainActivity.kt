package com.melancholicbastard.notificationfirebase

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.melancholicbastard.notificationfirebase.ui.theme.NotificationFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        NotificationUtil.createNotificationChannel(this)

        setContent {
            NotificationFirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick ={
                NotificationUtil.notifyNotification(this@MainActivity, "Притча о Стенли", "Стенли заметил, что на его компьютер так и не пришло ни одного распоряжения")
            }) {Text(
                text = "Hello $name!",
                modifier = modifier
            )}
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        NotificationFirebaseTheme {
            Greeting("Android")
        }
    }

    val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission(),
        ActivityResultCallback {

    })

}
