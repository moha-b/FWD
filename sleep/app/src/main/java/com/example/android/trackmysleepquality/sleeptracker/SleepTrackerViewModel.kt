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

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        // (application: Application) -> to access the style & layouts
        application: Application) : AndroidViewModel(application) {

                private var viewModelJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        private val tonight = MutableLiveData<SleepNight?>()
        private val nights = database.getTonight()
        init {
            initializeTonight()
        }

        private fun initializeTonight() {
                uiScope.launch {
                        tonight.value = getTonightFromDatabase()
                }
        }

        private suspend fun getTonightFromDatabase(): SleepNight? {
                return withContext(Dispatchers.IO){
                        var night = database.getTonight()
                        if (night?.endTime !=night?.startTime){
                                night = null
                        }
                        night
                }
        }

        fun startTracking(){
                uiScope.launch {
                        val newNight = SleepNight()
                        insert(newNight)
                }
        }

        fun stopTracing(){
                uiScope.launch {
                        val oldNight = tonight.value ?: return@launch
                        oldNight.endTime = System.currentTimeMillis()
                        update(oldNight)
                }
        }
        fun onClear(){
                uiScope.launch {
                        clear()
                        tonight.value = null
                }
        }
        private suspend fun clear(){
                withContext(Dispatchers.IO){
                        database.clear()
                }
        }

        // database calls on coroutines
        private suspend fun insert(newNight: SleepNight) {
                withContext(Dispatchers.IO){
                        database.insert(newNight)
                }
        }
        private suspend fun update(newNight: SleepNight) {
                withContext(Dispatchers.IO){
                        database.update(newNight)
                }
        }

}

