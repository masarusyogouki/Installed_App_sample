package com.example.installed_app_sample.viewmode

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.installed_app_sample.model.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppInfoViewModel() : ViewModel() {

    private val mutableappInfoList = MutableStateFlow<List<AppInfo>>(emptyList())
    val appInfoList: StateFlow<List<AppInfo>> = mutableappInfoList

    fun loadApps(context: Context) {
        val pm = context.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val resolveInfoList = pm.queryIntentActivities(mainIntent, 0)

        val appInfos = resolveInfoList.map { resolveInfo ->
            AppInfo(
                name = resolveInfo.loadLabel(pm).toString(),
                packageName = resolveInfo.activityInfo.packageName,
                icon = resolveInfo.loadIcon(pm)
            )
        }
        mutableappInfoList.value = appInfos
    }
}