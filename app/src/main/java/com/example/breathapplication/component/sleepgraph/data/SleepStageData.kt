package com.example.breathapplication.component.sleepgraph.data

import com.example.breathapplication.component.sleepgraph.item.SleepStageType

data class SleepStageData(
    val startTime: Float,
    val duration: Float,
    val stage: SleepStageType
)
