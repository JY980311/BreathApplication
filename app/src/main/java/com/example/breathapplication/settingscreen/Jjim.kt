package com.example.breathapplication.settingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.breathapplication.R
import com.example.breathapplication.settingnavigation.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Pick(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Greyscale11)
    ) {
        Header(navController = navController)
        Content()
    }
}

@Composable
fun Header(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Greyscale10)
                .padding(start = 16.dp, top = 15.dp)
        ) {
            Text(
                text = "찜 목록",
                style = Typography2.body1,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Greyscale2,
                modifier = Modifier.align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
                    .clickable{
                        navController.navigate(SettingNavItem.Setting.route)
                    }
            )
        }
    }
    Spacer(modifier = Modifier.height(143.dp))
}

@Composable
fun Content() {
    val imageUrls = generateImageUrls(4)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ImageRow(imageUrls.subList(0, 2))
        ImageRow(imageUrls.subList(2, 4))
    }
}

@Composable
fun ImageRow(imageUrls: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        for(url in imageUrls) {
            ImageBox(imageUrls = url)
        }
    }
}

@Composable
fun ImageBox(imageUrls: String) {
    var isHeartEnabled by remember { mutableStateOf(true) }
    val iconResource = if (isHeartEnabled) {
        R.drawable.icon_heart_enabled
    } else {
        R.drawable.vector_heart
    }

    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 160.dp)
            .background(
                color = Greyscale8,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Greyscale8,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        AsyncImage(
            model = imageUrls,
            contentDescription = "random image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(width = 150.dp, height = 160.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Image(
            painter = painterResource(id = iconResource),
            contentDescription = "icon_heart",
            modifier = Modifier
                .padding(10.dp)
                .clickable { isHeartEnabled = !isHeartEnabled }
                .align(Alignment.TopStart)
        )
    }
}

fun generateImageUrls(count: Int): List<String> {
    return List(count) {
        index -> "https://picsum.photos/seed/${index+1}/150/160"
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowPick() {
    /*
    Pick()
     */
}