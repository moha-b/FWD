/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.propertyanimation

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.google.samples.propertyanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            rotateButton.setOnClickListener {
                rotater()
            }

            translateButton.setOnClickListener {
                translater()
            }

            scaleButton.setOnClickListener {
                scaler()
            }

            fadeButton.setOnClickListener {
                fader()
            }

            colorizeButton.setOnClickListener {
                colorizer()
            }

            showerButton.setOnClickListener {
                shower()
            }
        }


    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }
            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

    private fun ActivityMainBinding.rotater() {
        ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f).apply {
            duration = 2000
            disableViewDuringAnimation(rotateButton)
            start()
        }
    }

    private fun ActivityMainBinding.translater() {
        ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f).apply {
            disableViewDuringAnimation(translateButton)
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    private fun ActivityMainBinding.scaler() {
        val scaleX: PropertyValuesHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY: PropertyValuesHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY).apply {
            disableViewDuringAnimation(scaleButton)
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 1
            interpolator = AccelerateInterpolator()
            start()
        }

    }

    private fun ActivityMainBinding.fader() {
        ObjectAnimator.ofFloat(star, View.ALPHA, 0f).apply {
            repeatCount = 5 * 2 + 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(fadeButton)
            start()
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun ActivityMainBinding.colorizer() {
        ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED, Color.GREEN,Color.BLUE).apply {
            duration = 2000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 1
            disableViewDuringAnimation(colorizeButton)
            start()
        }
    }

    private fun ActivityMainBinding.shower() {
        val container = star.parent as ViewGroup
        val containerH = container.height
        val containerW = container.width
        var starH = star.height.toFloat()
        var starW = star.width.toFloat()
        val newStar = AppCompatImageView(this@MainActivity).apply {
            setImageResource(R.drawable.ic_star)
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            container.addView(this)
            scaleX = Math.random().toFloat() * 1.5f + .1f
            scaleY = scaleX
            starH *= scaleX
            starW *= scaleY
            translationX = Math.random().toFloat() * containerW - starW / 2
        }


        val moverYAnimator = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH).apply {
            interpolator = AccelerateInterpolator(1f)
        }


        val rotationAnimator = ObjectAnimator.ofFloat(newStar, View.ROTATION, Math.random().toFloat() * 1080).apply {
            interpolator = LinearInterpolator()
        }

        AnimatorSet().apply {
            playTogether(moverYAnimator, rotationAnimator)
            duration = (Math.random() * 1500 + 500).toLong()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    container.removeView(newStar)
                }
            })
            start()
        }


    }


}
