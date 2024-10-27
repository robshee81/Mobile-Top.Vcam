package com.wangyiheng.vcamsx.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.wangyiheng.vcamsx.MainActivity

@Composable
fun DisclaimerDialog() {
    var showDialog by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val shengm = "Disclaimer\n" +
            "About this application\n" +
            "   This application (hereinafter referred to as the "application") aims to provide the function of replacing camera data. Users can change and adjust the data and images captured by the camera through this application. \n" +
            "\n" +
            "Terms of use\n" +
            "   Data processing: The user understands and agrees that all data and images processed through this application may include but are not limited to the content uploaded, modified and shared by the user. Users shall be fully responsible for the data and images they submit to the application. \n" +
            "\n" +
            "Legal use: The user agrees to use this application only for legal purposes and promises not to use this application for any illegal or unauthorised activities. \n" +
            "\n" +
            "Copyright and Intellectual Property Rights: Users guarantee to own or legally authorise the use of all relevant rights of all data and images processed through this application, including but not limited to copyright, trademark rights and patent rights. \n" +
            "\n" +
            "Privacy protection: Users shall respect the privacy rights of others and promise not to collect, process or distribute other people's personal information through this application unless express authorisation is obtained. \n" +
            "\n" +
            "Limitation of liability: The developer shall not be liable for any direct or indirect consequences arising from the user's use of this application. Users shall bear any risks and consequences that may be caused by the use of this application. \n" +
            "\n" +
            "Disclaimer: The developer hereby declares that he is not responsible for the following matters: \n" +
            "   1. Any damage or loss caused by the user's improper use of this application. \n" +
            "   2. The use or reliance of any third party on this application. \n" +
            "   3. Any indirect, incidental, special, punitive or consequential damage caused by the use or inability to use this application. \n" +
            "   4. Any data loss caused by unauthorised access or use of this application. \n" +
            "\n" +
            "Any modification to this disclaimer will be updated on this application or official website and will take effect from the date of publication. Users who continue to use this application will be deemed to have accepted the revised disclaimer. \n" +
            "\n" +
            "Legal application\n" +
            "The interpretation and application of this disclaimer shall comply with relevant laws and regulations. Any dispute arising from this application shall be submitted to a court with jurisdiction. "

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(text = "Disclaimer")
            },
            text = {
                // If the text of the disclaimer is short, you can consider using Column + Scrollable
                Box(modifier = Modifier.heightIn(max = 300.dp)) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        shengm.split("\n").forEach { line ->
                            Text(text = line)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("I agree.")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        closeApp(context)
                    }
                ) {
                    Text("I don't agree")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

private fun closeApp(context: Context) {
    // Stop the relevant services
    // Take for example：douyin.stop() 和 cloudPlatform.disableAutoRotate()
    // Close all activities
    if (context is MainActivity) {
        context.finishAffinity()
    }
}
