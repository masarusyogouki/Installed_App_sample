package com.example.installed_app_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.installed_app_sample.view.AppInfoView
import com.example.installed_app_sample.viewmode.AppInfoViewModel

class MainActivity : ComponentActivity() {

    private val appInfoViewModel by viewModels<AppInfoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val appInfoList by appInfoViewModel.appInfoList.collectAsState()

            LaunchedEffect(key1 = "loadApps") {
                appInfoViewModel.loadApps(this@MainActivity)
            }

            LazyColumn {
                items(appInfoList) { appInfo ->
                    AppInfoView(appInfo = appInfo, viewModel = appInfoViewModel)
                }
            }
        }
    }
}