package com.example.installed_app_sample.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.installed_app_sample.model.AppInfo
import com.example.installed_app_sample.viewmode.AppInfoViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun AppInfoView(
    appInfo: AppInfo,
    viewModel: AppInfoViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp),
            painter = rememberDrawablePainter(appInfo.icon),
            contentDescription = appInfo.packageName
        )
        Text(text = appInfo.name)
    }
}