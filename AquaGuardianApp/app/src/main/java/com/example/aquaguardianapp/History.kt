package com.example.aquaguardianapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import retrofit2.http.GET

data class Message(
    val message: String,
    val device: String,
    val timestamp: String,
    val date: String,
   // val longitude: String,
//    val latitude: String
)

interface ApiService {
    @GET("messages")
    suspend fun getMessage(): List<Message>
}

object ApiClient{
    private const val BASE_URL = "https://demo2971444.mockable.io/"
private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}


@Composable
fun MessageList(messages: List<Message>) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "|Device|", fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold,modifier = Modifier.padding(end = 10.dp))
        Text(text = "|Status|", fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold,modifier = Modifier.padding(end = 10.dp))
        Text(text = "|Time|",fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold,modifier = Modifier.padding(end = 10.dp))
        Text(text = "|Date|",fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold,modifier = Modifier.padding(end = 10.dp, start = 20.dp))


    }
    Divider(color = Color.Black, thickness = 3.dp,)
    LazyColumn {
        itemsIndexed(messages) {index, message ->
            MessageRow(message,index)

        }
    }
}

@Composable
private fun MessageRow(message: Message,index: Int) {
    val backgroundColor = if (index % 2 == 0) Color(0xFF64C9FF) else Color(0xFF00C2FF)
    val qualityColor = if (message.message == " Clean ") Color(0xFF00FF00) else Color(0xFFFF0000)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = message.device, fontSize = 22.sp, color = Color.White)
        Text(text = message.message, fontSize = 22.sp, color = qualityColor)
        Text(text = message.timestamp,fontSize = 22.sp, color = Color.White)
        Text(text = message.date,fontSize = 22.sp, color = Color.White)
 //       Text(text = message.longitude,fontSize = 20.sp, color = Color.White)
//        Text(text = message.latitude, overflow = TextOverflow.Clip)

    }
    Divider(color = Color.Black, thickness = 1.dp,)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun history(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MessageViewModel = viewModel(),

)

{ LaunchedEffect(viewModel) {
        viewModel.loadMessages()
    }

    val messages by viewModel.messages.observeAsState(listOf())


    Log.d("History", "History page called")



    if (messages.isEmpty()) {
        Log.d("History", "Messages is empty")
    } else {
        Log.d("History", "Messages is not empty")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { backButton() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        "History",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive)
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 70.dp)
                    .background(Color(0xFF00C2FF)),
            ) {
                MessageList(messages = listOf(
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        " 23/11/2023 ",
//                        "-90.33123",
//                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        " 23/11/2023 ",
//                        "-91.22123",
//                            "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        " 23/11/2023 ",
//                        "-92.78643",
//                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        " 24/11/2023 ",
//                         "-90.33123",
//                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        " 24/11/2023 ",
//                         "-91.22123",
//                            "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        " 24/11/2023 ",
//                         "-92.78643",
//                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        " 25/11/2023 ",
//                         "-90.33123",
//                              "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        " 25/11/2023 ",
//                         "-91.22123",
                        //                             "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        " 25/11/2023 ",
                        //                        "-92.78643",
                        //                             "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        " 26/11/2023 ",
                        //  "-90.33123",
                        //                             "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        " 26/11/2023 ",
                        //   "-91.22123",
                        //                            "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        " 26/11/2023 ",
                        //   "-92.78643",
                        //                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        " 27/11/2023 ",
                        // "-90.33123",
                        //                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        " 27/11/2023 ",
                        //  "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        " 27/11/2023 ",
                        //  "-92.78643",
                        //                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        " 28/11/2023 ",
                        //   "-90.33123",
                        //                          "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        " 28/11/2023 ",
                        //   "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        " 28/11/2023 ",
                        //  "-92.78643",
                        //                          "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        " 28/11/2023 ",
                        //   "-90.33123",
                        //                           "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        " 28/11/2023 ",
                        //    "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "12:46",
                        " 28/11/2023 ",
                        //    "-92.78643",
                        //                           "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        " 28/11/2023 ",
                        //  "-90.33123",
                        //                             "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        " 29/11/2023 ",
                        // "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        " 29/11/2023 ",
                        //  "-92.78643",
                        //                          "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        " 29/11/2023 ",
                        //  "-90.33123",
                        //                           "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        " 29/11/2023 ",
                        //   "-91.22123",
                        //       "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        " 29/11/2023 ",
                        // "-92.78643",
                        //        "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        " 30/11/2023 ",
                        //   "-90.33123",
                        //        "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        " 30/11/2023 ",
                        // "-91.22123",
                        //        "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        " 30/11/2023 ",
                        //  "-92.78643",
                        //         "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        " 30/11/2023 ",
                        //  "-90.33123",
                        //        "38.7545"
                    ),
                    Message(
                        " Dirty ",
                        "Device 2",
                        "18:55",
                        " 30/11/2023 ",
                        //  "-91.22123",
                        //       "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "22:35",
                        " 30/11/2023 ",
                        // "-92.78643",
                        //       "41.1145"
                    ),
                )
                )
            }
        }
    )
