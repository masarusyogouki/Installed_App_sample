package com.example.installed_app_sample

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.installed_app_sample.ui.theme.Installed_App_sampleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Installed_App_sampleTheme {
                AppListScreen()
            }
        }
    }
}

data class AppInfo(
    val name: String,
    val icon: Drawable?
)

fun getAppInfo(context: Context): List<AppInfo> {
    val pm = context.packageManager
    val flag: Int = PackageManager.GET_META_DATA
    val packages: List<PackageInfo> = pm.getInstalledPackages(flag)

    val appItems = mutableListOf<AppInfo>()
    for (packagesInfo in packages) {
        val appName = packagesInfo.applicationInfo.loadLabel(pm).toString()
        val appIcon = packagesInfo.applicationInfo.loadLogo(pm)
        appItems.add(AppInfo(name = appName, icon = appIcon))
    }
    return appItems
}

@Composable
fun AppListScreen() {
    val appInfoList = getAppInfo(LocalContext.current)

    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(appInfoList) {appInfo ->
            AppItem(appInfo = appInfo)
        }
    }
}

@Composable
fun AppItem(appInfo: AppInfo) {
    Column {
        Text(
            text = appInfo.name
        )
    }
}