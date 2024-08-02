package com.example.breathapplication.component.sleepgraph.data

import com.example.breathapplication.component.sleepgraph.item.SnoringStageType

data class SnoringStageData (
    val startTime: Float,
    val duration: Float,
    val stage: SnoringStageType
)