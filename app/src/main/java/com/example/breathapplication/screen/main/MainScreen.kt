package com.example.breathapplication.screen.main

import android.os.Build
import android.text.style.TtsSpan.TextBuilder
import android.util.Log
import android.widget.GridLayout
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.calendar.ui.CalendarScreen
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.component.sleepgraph.SleepGraph
import com.example.breathapplication.component.sleepgraph.SnoringGraph
import com.example.breathapplication.network.model.asleep.AsleepData
import com.example.breathapplication.network.test.TestViewModel
import com.example.breathapplication.screen.main.main_component.ChatBotBox
import com.example.breathapplication.screen.main.main_component.ImageBox
import com.example.breathapplication.screen.main.main_component.SleepInfoBox
import com.example.breathapplication.screen.main.main_component.SleepScoreGraph
import com.example.breathapplication.screen.main.main_component.SleepTimeIcon
import com.example.breathapplication.screen.main.main_component.SleepVerticalGraph
import com.example.breathapplication.screen.main.main_component.WakeTimeIcon
import com.example.breathapplication.screen.main.main_component.Wave
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale3
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Greyscale9
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Secondary1
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel
import com.example.breathapplication.viewmodel.MainScreenViewModel
import okhttp3.internal.wait
import kotlin.math.roundToInt
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    diaryScreenViewModel : DiaryScreenViewModel,
    navController: NavHostController
) {

    val asleepData = viewModel.asleepData.collectAsStateWithLifecycle()

    /** 취침 시간 */
    val sleepTimeData = asleepData.value.result.stat.sleep_time

    /** 일어난 시간 */
    val wakeTimeData = asleepData.value.result.stat.wake_time

    /** 코골이 횟수 */
    val snoringCount = asleepData.value.result.stat.snoring_count

    /** 코골이 시간 */
    val snoringTime = asleepData.value.result.stat.time_in_snoring

    /** 총 수면 시간 */
    val totalSleepTimeHour = asleepData.value.result.stat.time_in_sleep.div(3600)

    /** 총 수면 시간의 분 */
    val totalSleepTimeMin = asleepData.value.result.stat.time_in_sleep.rem(3600) / 60

    /** 수면 시간 split 해서 원하는 부분 추출 */
    val sleepTime: String? = try {
        val dateTimeParts = sleepTimeData.split("T")
        if (dateTimeParts.size > 1) {
            val timeParts = dateTimeParts[1].split(":")
            if (timeParts.isNotEmpty()) {
                timeParts[0] + ":" + timeParts[1]
            } else {
                null
            }
        } else {
            null
        }
    } catch (e: Exception) {
        Log.e("sleepStages", "Error Sleep Time", e)
        null
    }

    /** 기상 시간 split 해서 원하는 부분 추출 */
    val wakeTime: String? = try {
        val dateTimeParts = wakeTimeData.split("T")
        if (dateTimeParts.size > 1) {
            val timeParts = dateTimeParts[1].split(":")
            if (timeParts.isNotEmpty()) {
                timeParts[0] + ":" + timeParts[1]
            } else {
                null
            }
        } else {
            null
        }
    } catch (e: Exception) {
        Log.e("sleepStages", "Error Wake Time", e)
        null
    }

    /** 잠들기까지 걸린 시간 */
    val timeToFallAsleepData = asleepData.value.result.stat.sleep_latency
    val timeToFallAsleep = if (timeToFallAsleepData < 3600) {
        "${timeToFallAsleepData.div(60)}분"
    } else {
        "${timeToFallAsleepData.div(3600)}시간 ${timeToFallAsleepData.rem(3600).div(60)}분"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        TobBar(title = diaryScreenViewModel.TopbarDate.value, R.drawable.ic_calendar, R.drawable.ic_setting, navController, diaryScreenViewModel = diaryScreenViewModel)
        if(diaryScreenViewModel.isCalendarClicked.value){
            CalendarScreen(
                diaryScreenViewModel = diaryScreenViewModel
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                text = "7월 21일 나의 수면 Test 버전",
                style = Typography2.h1,
                color = Greyscale2
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "홍길동님! 좋은 아침이에요.\n오늘 홍길동님의 수면 상태를 살펴볼까요?",
                style = Typography2.subTitle,
                color = Greyscale5
            )

            Wave(
                modifier = Modifier.padding(top = 30.dp, bottom = 25.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                    contentDescription = "별모양",
                    tint = Greyscale6
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "절망에서 희망으로 건너가는 가장 좋은\n다리는 밤에 단잠을 자는 것이다.",
                    style = Typography2.body1,
                    color = Primary2
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            SleepTime(
                modifier = Modifier,
                sleepTime = sleepTime ?: "잘 못된 정보 입니다.",
                wakeTime = wakeTime ?: "잘 못된 정보 입니다."
            )

            Column(
                modifier = Modifier.padding(top = 34.dp, bottom = 18.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "총 수면 시간",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )
                Text(
                    text = "${totalSleepTimeHour}시간 " + "${totalSleepTimeMin}분",
                    style = Typography2.title,
                    color = Greyscale2
                )
            }

            SleepGraph(viewModel = MainScreenViewModel())

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(4) {
                    SleepInfoBox(
                        title = when (it) {
                            0 -> "비수면"
                            1 -> "얕은잠"
                            2 -> "깊은잠"
                            3 -> "렘수면"
                            else -> "잘못된 정보"
                        },
                        sleepTime = when (it) {
                            0 -> asleepData.value.result.stat.time_in_wake
                            1 -> asleepData.value.result.stat.time_in_light
                            2 -> asleepData.value.result.stat.time_in_deep
                            3 -> asleepData.value.result.stat.time_in_rem
                            else -> 0
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Column(
                modifier = Modifier.padding(bottom = 18.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "잠들기까지 걸린 시간",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Text(
                    text = timeToFallAsleep,
                    style = Typography2.title,
                    color = Greyscale2
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Greyscale8, RoundedCornerShape(12.dp))
                    .background(Greyscale10)
                    .padding(vertical = 16.dp, horizontal = 21.dp)
            ) {
                Text(
                    text = "고민이 많아 쉽게 잠에 들지 못하셨나요?\n일기를 쓰며 하루를 마무리하는 건 어떨까요?",
                    style = Typography2.body1,
                    color = Primary2
                )
            }

            Wave(
                modifier = Modifier.padding(vertical = 40.dp)
            )

            Column(
                modifier = Modifier.padding(bottom = 100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "AI 기반의 챗봇에게 수면 상담을\n받을 수도 있어요.",
                    style = Typography2.subHead,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )

                ChatBotBox(
                    modifier = Modifier.padding(top = 36.dp, bottom = 14.dp)
                )

                Text(
                    text = "전송을 눌러 바로 상담을 받아보세요!",
                    style = Typography2.bodyText,
                    color = Greyscale5
                )
            }

            Column {
                Text(
                    text = "나의 코골이 횟수는",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = snoringCount.toString() + "회",
                    style = Typography2.title,
                    color = Greyscale2
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "나의 코골이를 한 시간은",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = calculateTime(snoringTime),
                    style = Typography2.title,
                    color = Greyscale2
                )

                Spacer(modifier = Modifier.height(10.dp))

                SnoringGraph(viewModel = MainScreenViewModel())
            }

            Spacer(modifier = Modifier.height(100.dp))

            Column {
                Text(
                    text = "나의 평균 수면 점수 및 각 수면의 비율은?",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Spacer(modifier = Modifier.height(12.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Greyscale10)
                ) {
                    SleepStats(
                        modifier = Modifier.align(Alignment.Center),
                        asleepData = asleepData.value
                    )
                }
            }

            Spacer(modifier = Modifier.height(74.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "편안한 수면을 위해\n홍길동님께 추천드릴게요!",
                style = Typography2.subHead,
                color = Greyscale2,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    ),
                ) {
                    ImageBox(image = R.drawable.im_pd)
                    ImageBox(image = R.drawable.im_pd)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    ImageBox(image = R.drawable.im_pd)
                    ImageBox(image = R.drawable.im_pd)
                }
            }
        }
    }
}



/** 취침 시간 및 일어난 시간 부분 */
@Composable
fun SleepTime(
    modifier: Modifier,
    sleepTime: String,
    wakeTime: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Greyscale10)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 18.dp)
                    .padding(top = 14.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Text(
                    text = "취침 시간",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    SleepTimeIcon()
                    Text(
                        text = sleepTime,
                        style = Typography2.title,
                        color = Greyscale2
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Greyscale8)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 14.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Text(
                    text = "일어난 시간",
                    style = Typography2.subTitle,
                    color = Greyscale5
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    WakeTimeIcon()
                    Text(
                        text = wakeTime,
                        style = Typography2.title,
                        color = Greyscale2
                    )
                }
            }
        }
    }
}


/**
 * 일단 잠시 만들어 놓은 것이므로 추후 수정이 필요함
 * 아래 부분까지 포함
 * */
@Composable
fun SleepStatColumn(
    modifier: Modifier = Modifier,
    angle: Double? = null,
    size: Double? = null,
    title: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (angle != null) {
            SleepScoreGraph(
                modifier = modifier,
                angle = angle
            )
        } else if (size != null) {
            SleepVerticalGraph(size = size)
        }
        Text(
            text = title,
            style = Typography2.body3,
            color = Greyscale5
        )
    }
}

@Composable
fun SleepStats(
    modifier: Modifier = Modifier,
    asleepData: AsleepData // replace with your actual data type
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        SleepStatColumn(
            angle = asleepData.result.stat.sleep_ratio,
            title = "수면 점수"
        )
        SleepStatColumn(
            size = asleepData.result.stat.wake_ratio,
            title = "비수면"
        )
        SleepStatColumn(
            size = asleepData.result.stat.light_ratio,
            title = "얕은잠"
        )
        SleepStatColumn(
            size = asleepData.result.stat.deep_ratio,
            title = "깊은잠"
        )
        SleepStatColumn(
            size = asleepData.result.stat.rem_ratio,
            title = "렘수면"
        )
    }
}

/** 급하게 만든 유틸 함수 */
fun calculateTime(time: Int): String {
    return if (time < 3600) {
        "${time.div(60)}분"
    } else if (time < 86400) {
        "${time.div(3600)}시간 ${time.rem(3600).div(60)}분"
    } else {
        "잘못된 정보"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, backgroundColor = 0xFF111215)
@Composable
fun DefaultHandler2Preview() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111215))
    ) {
        MainScreen(viewModel = MainScreenViewModel(), diaryScreenViewModel = DiaryScreenViewModel(), navController = navController)
    }
}


