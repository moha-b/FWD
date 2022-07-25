/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_data")
data class SleepNight(

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,

    @ColumnInfo(name = "start_sleep")
    val startTime:Long = System.currentTimeMillis(),

    @ColumnInfo(name = "fin_sleep")
    var endTime:Long = startTime,
    // rate = -1 to refer that the user didn't put a rate
    @ColumnInfo(name ="rate")
    val rate: Int = -1
)