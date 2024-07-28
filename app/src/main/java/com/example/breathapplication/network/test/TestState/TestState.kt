package com.example.breathapplication.network.test.TestState

import com.example.breathapplication.network.model.asleep.AsleepData
import com.example.breathapplication.network.model.asleep.Result
import com.example.breathapplication.network.model.asleep.Session
import com.example.breathapplication.network.model.asleep.Stat

data class TestState(
    val asleepDataState: AsleepData = AsleepData(
        "detail",
        Result(
            0.0,
            emptyList(),
            Session(
                emptyList(),
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                "",
                "",
                ""
            ),
            Stat(
                0.0,
                "",
                0,
                0.0,
                0,
                0.0,
                0,
                0.0,
                0,
                0.0,
                0,
                0,
                emptyList(),
                0.0,
                0,
                0,
                0.0,
                "",
                0,
                0.0,
                0.0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0.0,
                0.0,
                "",
                0,
                0
            ),
            "timezone"
        )
    )
)