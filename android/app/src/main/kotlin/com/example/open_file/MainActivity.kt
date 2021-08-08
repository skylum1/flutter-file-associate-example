package com.example.open_file

import io.flutter.embedding.android.FlutterActivity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
    // TODO: CHANGE METHOD CHANNEL NAME
    private val CHANNEL = "YOUR_CHANNEL_NAME"

    var openPath: String? = null
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "getOpenFileUrl" -> {
                    result.success(openPath)
                }
                else -> result.notImplemented()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleOpenFileUrl(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleOpenFileUrl(intent)
    }

    private fun handleOpenFileUrl(intent: Intent?) {
        val path = intent?.data?.path
        if (path != null) {
            openPath = path
        }
    }
}