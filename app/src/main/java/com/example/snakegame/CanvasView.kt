package com.example.snakegame

import android.view.View
import android.util.AttributeSet
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val snakeBody = Paint() // Создаем объект Paint для рисования змейки
        snakeBody.setColor(Color.GREEN) // Устанавливаем цвет змейки (зеленый)
        val food = Paint() // Создаем объект Paint для рисования еды
        food.setColor(Color.RED) // Устанавливаем цвет еды (красный)
        val level = Paint() // Создаем объект Paint для рисования уровня
        level.setColor(Color.DKGRAY) // Устанавливаем цвет уровня (темно-серый)

        // Рисуем уровень (прямоугольник)
        canvas?.drawRect(0f, 0f, 1050f, 1050f, level)

        // Рисуем змейку из массива (прямоугольники для каждого сегмента змейки)
        for (i in Snake.bodyParts) {
            canvas?.drawRect(i[0], i[1], i[0] + 45, i[1] + 45, snakeBody)
        }

        // Рисуем еду из массива (прямоугольник для еды)
        canvas?.drawRect(Food.posX, Food.posY, Food.posX + 45, Food.posY + 45, food)
    }
}