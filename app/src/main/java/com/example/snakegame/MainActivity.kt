package com.example.snakegame

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()

        open class OnSwipeTouchListener : View.OnTouchListener {
            private val gestureDetector = GestureDetector(GestureListener())

            fun onTouch(event: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(event)
            }

            private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
                private val SWIPE_THRESHOLD = 100
                private val SWIPE_VELOCITY_THRESHOLD = 100

                override fun onDown(e: MotionEvent): Boolean {
                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    onTouch(e)
                    return true
                }

                override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                    val result = false
                    try {
                        val diffY = e2.y - e1.y
                        val diffX = e2.x - e1.x
                        if (abs(diffX) > abs(diffY)) {
                            if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                                if (diffX > 0) {
                                    onSwipeRight()
                                } else {
                                    onSwipeLeft()
                                }
                            }
                        } else {
                            if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                                if (diffY > 0) {
                                    onSwipeTop()
                                } else {
                                    onSwipeBottom()
                                }
                            }
                        }
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                    return result
                }
            }

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(event)
            }

            open fun onSwipeRight() {}
            open fun onSwipeLeft() {}
            open fun onSwipeTop() {}
            open fun onSwipeBottom() {}
        }

        canvas.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                Snake.alive = true
                if (Snake.direction != "right")
                    Snake.direction = "left"
            }

            override fun onSwipeRight() {
                Snake.alive = true
                if (Snake.direction != "left")
                    Snake.direction = "right"
            }

            override fun onSwipeTop() {
                Snake.alive = true
                if (Snake.direction != "up")
                    Snake.direction = "down"
            }

            override fun onSwipeBottom() {
                Snake.alive = true
                if (Snake.direction != "down")
                    Snake.direction = "up"
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                while (Snake.alive) {
                    when (Snake.direction) {
                        "up" -> {
                            Snake.headY -= 50
                            if (!Snake.possibleMove() || Snake.checkCollision()) {
                                Snake.alive = false
                                Snake.reset()
                            }
                        }
                        "down" -> {
                            Snake.headY += 50
                            if (!Snake.possibleMove() || Snake.checkCollision()) {
                                Snake.alive = false
                                Snake.reset()
                            }
                        }
                        "left" -> {
                            Snake.headX -= 50
                            if (!Snake.possibleMove() || Snake.checkCollision()) {
                                Snake.alive = false
                                Snake.reset()
                            }
                        }
                        "right" -> {
                            Snake.headX += 50
                            if (!Snake.possibleMove() || Snake.checkCollision()) {
                                Snake.alive = false
                                Snake.reset()
                            }
                        }
                    }
                    Snake.bodyParts.add(arrayOf(Snake.headX, Snake.headY))

                    if (Snake.headX == Food.posX && Snake.headY == Food.posY)
                        Food.generate()
                    else
                        Snake.bodyParts.removeAt(0)

                    canvas.invalidate()
                    delay(150)
                }
            }
        }

        button_up.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "down")
                Snake.direction = "up"
        }
        button_down.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "up")
                Snake.direction = "down"
        }
        button_left.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "right")
                Snake.direction = "left"
        }
        button_right.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "left")
                Snake.direction = "right"
        }
    }
}
