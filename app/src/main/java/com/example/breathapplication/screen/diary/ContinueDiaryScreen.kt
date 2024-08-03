package com.example.breathapplication.screen.diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.calendar.ui.CalendarScreen
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.component.ConditionButton
import com.example.breathapplication.component.MoodTag
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.navigation.diary.DiaryNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale3
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel
import kotlin.math.abs
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContinueDiaryScreen(navController: NavHostController, diaryScreenViewModel: DiaryScreenViewModel){
    diaryScreenViewModel.isComplete.value = false
    Column(
        Modifier
            .background(color = Greyscale11)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        TobBar(title = diaryScreenViewModel.TopbarDate.value, R.drawable.ic_calendar, R.drawable.ic_setting, navController, diaryScreenViewModel = diaryScreenViewModel)
        if(diaryScreenViewModel.isCalendarClicked.value){
            CalendarScreen(diaryScreenViewModel)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp),
        ){
            item {
                MyDiaryScreen(diaryScreenViewModel)
            }
            item{
                Column {
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_wavy_line),
                            contentDescription = "물결 아이콘",
                            tint = Color.Unspecified,
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "수면 도중 어떤 일이 일어났나요?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SleepDisturbTag(diaryScreenViewModel)

                    Spacer(modifier = Modifier.height(52.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "잠에서 깬 직후 어떤 상태였나요?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ConditionTag(diaryScreenViewModel)
                    Spacer(modifier = Modifier.height(54.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "오늘 나의 수면 만족도는?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                    SlideButton(diaryScreenViewModel)
                    Spacer(modifier = Modifier.height(38.dp))
                    CompleteButton(text = "작성 완료", onClick = {
                        diaryScreenViewModel.postApiTest()
                        diaryScreenViewModel.continueShowDialog = true
                        diaryScreenViewModel.setCompleteSleepTag(diaryScreenViewModel.continueSleepTag.value)
                        diaryScreenViewModel.setCompleteConditionTag(diaryScreenViewModel.continueConditionTag.value)
                    })

                    Spacer(modifier = Modifier.height(120.dp))
                }

            }

        }
    }
    if(diaryScreenViewModel.continueShowDialog){
        CompleteDialog2(diaryScreenViewModel = diaryScreenViewModel, navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConditionTag(diaryScreenViewModel: DiaryScreenViewModel) {
    val tagList = listOf("개운했어요","피곤했어요")

    val itemsPerRow = 2
    val rows = tagList.chunked(itemsPerRow)

    var selectedTag by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { tag ->
                    ConditionButton(tag,
                        isSelected = tag == selectedTag,
                        onClick = {
                            selectedTag = if (tag == selectedTag) null else tag
                            diaryScreenViewModel.setContinueConditionTag(tag)
                        })
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepDisturbTag(diaryScreenViewModel: DiaryScreenViewModel){
    val tagList = listOf("푹 잤어요","뒤척였어요","자주 깼어요","그냥 잤어요", "꿈을 많이 꿨어요")

    val itemsPerRow = 3
    val rows = tagList.chunked(itemsPerRow)

    var selectedTag by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { tag ->
                    MoodTag(tag,
                        isSelected = tag == selectedTag,
                        onClick = {
                            selectedTag = if (tag == selectedTag) null else tag
                            diaryScreenViewModel.setContinueSleepTag(tag)
                        }
                        )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompleteDialog2(diaryScreenViewModel: DiaryScreenViewModel, navController: NavHostController) {
    Dialog(onDismissRequest = { diaryScreenViewModel.continueShowDialog = false },) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(368.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Greyscale10) // 배경색 지정
                    .padding(20.dp, 62.dp, 20.dp, 42.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sheep),
                    contentDescription = "amount",
                    tint = Color(0xFF5F7CFE),
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "일기를 모두 완성했어요!\n완성된 일기는 나의 수면을 되돌아보는 데\n도움이 될 거에요.\n\n숨소리가 ${diaryScreenViewModel.name.value}님의 꿀잠을\n응원할게요!",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))

                CompleteButton("완성된 일기 보러가기", onClick = {
                    diaryScreenViewModel.continueShowDialog = false
                    navController.navigate(DiaryNavItem.CompleteDiaryScreen.route)
                })

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SlideButton(diaryScreenViewModel: DiaryScreenViewModel) {
    var offsetX by remember { mutableStateOf(0f) }
    var barWidth by remember { mutableStateOf(0f) }
    val buttonSize = 28.dp
    val density = LocalDensity.current.density
    val buttonSizePx = buttonSize.toPx(density)
    var maxWidth = 0.0f

    // 원 반지름과 마진
    val pointRadius = 6.dp.toPx(density)
    val pointMargin = 5.dp.toPx(density)

    // 원 위치
    val points = remember { mutableStateListOf<Float>() }
    var startPosition by remember { mutableStateOf(0f) }

    val pointValues = listOf(1, 2, 3, 4, 5)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .align(Alignment.Center)
                    .onGloballyPositioned { coordinates ->
                        barWidth = coordinates.size.width.toFloat()
                        points.clear()
                        points.addAll(
                            listOf(
                                pointRadius + pointMargin, // 첫 번째 원 위치
                                barWidth / 4f, // 두 번째 원 위치
                                barWidth / 2f, // 세 번째 원 위치
                                3 * barWidth / 4f, // 네 번째 원 위치
                                barWidth - pointRadius - pointMargin // 마지막 원 위치
                            )
                        )
                    }
            ) {
                maxWidth = size.width
                drawRoundRect(
                    color = Greyscale10,
                    size = Size(width = size.width, height = 16.dp.toPx(density)),
                    cornerRadius = CornerRadius(32.dp.toPx(density), 32.dp.toPx(density))
                )
                drawRoundRect(
                    color = Primary1,
                    size = Size(
                        width = (offsetX + buttonSizePx / 2).coerceIn(0f, size.width),
                        height = 16.dp.toPx(density)
                    ),
                    cornerRadius = CornerRadius(32.dp.toPx(density), 32.dp.toPx(density))
                )

                points.forEach { x ->
                    val pointColor = if (x <= offsetX + buttonSizePx / 2) Greyscale3 else Greyscale7
                    drawCircle(
                        color = pointColor,
                        radius = pointRadius,
                        center = Offset(x, size.height / 2)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset((offsetX).roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { startPosition = offsetX  },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val newOffsetX =
                                    (offsetX + dragAmount.x).coerceIn(0f, maxWidth - buttonSizePx)
                                offsetX = newOffsetX
                            },
                            onDragEnd = {
                                val closestPoint = points.filter { it <= offsetX }
                                    .minByOrNull { abs(it - offsetX) }
                                    ?: points.minByOrNull { abs(it - offsetX) }
                                    ?: startPosition

                                // 마지막 원의 위치
                                val lastPoint = points.last()

                                // 마지막 원이 가장 가까운 경우
                                if (abs(offsetX - lastPoint) < abs(offsetX - closestPoint)) {
                                    offsetX = lastPoint - buttonSizePx / 2
                                    diaryScreenViewModel.setSlideValue(5)
                                } else {
                                    offsetX = closestPoint - buttonSizePx / 2
                                    diaryScreenViewModel.setSlideValue(pointValues[points.indexOf(closestPoint)])
                                }
                            }
                        )
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_moon),
                    contentDescription = "moon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(buttonSize)
                )
            }
        }

        Spacer(modifier = Modifier.height(9.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("BAD", style = Typography2.body4, color = Primary1)
            Text("SOSO", style = Typography2.body4, color = Primary1)
            Text("GOOD!", style = Typography2.body4, color = Primary1)
        }
    }
}

private fun Dp.toPx(density: Float): Float {
    return this.value * density
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun ContinueDiaryScreenPreview() {
    val navController = rememberNavController()
    ContinueDiaryScreen(
        navController = navController,
        diaryScreenViewModel = DiaryScreenViewModel()
    )
}