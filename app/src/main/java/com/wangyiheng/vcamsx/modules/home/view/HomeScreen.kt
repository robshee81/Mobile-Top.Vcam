import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wangyiheng.vcamsx.components.LivePlayerDialog
import com.wangyiheng.vcamsx.components.SettingRow
import com.wangyiheng.vcamsx.components.VideoPlayerDialog
import com.wangyiheng.vcamsx.modules.home.controllers.HomeController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val homeController =  viewModel<HomeController>()
    LaunchedEffect(Unit){
        homeController.init()
    }

    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            homeController.copyVideoToAppDir(context,it)
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                selectVideoLauncher.launch("video/*")
            } else {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    // 在 Android 9 (Pie) 及以下版本，请求 READ_EXTERNAL_STORAGE 权限
                    Toast.makeText(context, "请打开设置允许读取文件夹权限", Toast.LENGTH_SHORT).show()
                } else {
                    // 在 Android 10 及以上版本，直接访问视频文件，无需请求权限
                    selectVideoLauncher.launch("video/*")
                }
            }
        }
    )

    Card(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        val buttonModifier = Modifier
            .fillMaxWidth()

        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = homeController.liveURL.value,
                onValueChange = { homeController.liveURL.value = it },
                label = { Text("RTMP Link：") }
            )

            Button(
                modifier = buttonModifier,
                onClick = {
                    homeController.saveState()
                }
            ) {
                Text("Save RTMP Link")
            }
            Button(
                modifier = buttonModifier,
                onClick = {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

                }
            ) {
                Text("Select Video")
            }

            Button(
                modifier = buttonModifier,
                onClick = {
                    homeController.isVideoDisplay.value = true
                }
            ) {
                Text("View Video")
            }

            Button(
                modifier = buttonModifier,
                onClick = {
                    homeController.isLiveStreamingDisplay.value = true
                }
            ) {
                Text("View Live Push Stream")
            }

            SettingRow(
                label = "Video Switch",
                checkedState = homeController.isVideoEnabled,
                onCheckedChange = { homeController.saveState() },
                context = context
            )

            SettingRow(
                label = "Live Streaming Switch",
                checkedState = homeController.isLiveStreamingEnabled,
                onCheckedChange = { homeController.saveState() },
                context = context
            )

            SettingRow(
                label = "Volume Switch",
                checkedState = homeController.isVolumeEnabled,
                onCheckedChange = { homeController.saveState() },
                context = context
            )

            SettingRow(
                label = if (homeController.codecType.value) "Hard Decoding" else "Soft Decoding",
                checkedState = homeController.codecType,
                onCheckedChange = {
                    if(homeController.isH264HardwareDecoderSupport()){
                        homeController.saveState()
                    }else{
                        homeController.codecType.value = false
                        Toast.makeText(context, "Hard Decoding is not Supported", Toast.LENGTH_SHORT).show()
                    }},
                context = context
            )
        }
        val annotatedString = AnnotatedString.Builder("More Features are in the works...").apply {
            // Add Scope for click event
            addStringAnnotation(
                tag = "URL",
                annotation = "None",
                start = 0,
                end = 12
            )
        }.toAnnotatedString()

        ClickableText(
            text = annotatedString,
            style = TextStyle(fontSize = 12.sp, textDecoration = TextDecoration.Underline)
        ) { offset ->
            annotatedString.getStringAnnotations("URL", offset, offset)
                .firstOrNull()?.let { annotation ->
                    // Handle click event here，For example, open a browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                    context.startActivity(intent)
                }
        }

        LivePlayerDialog(homeController)
        VideoPlayerDialog(homeController)
    }
}


@Preview
@Composable
fun PreviewMessageCard() {
    HomeScreen()
}
