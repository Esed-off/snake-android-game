package com.example.snakegame

import android.content.Context
import android.content.Intent

class Food {
    companion object {
        var posX = 500f // Начальная позиция X
        var posY = 500f // Начальная позиция Y

        fun generate(context: Context) {
            // Генерация новой позиции
            posX = (1..20).random().toFloat() * 50 // Случайное значение X в диапазоне [50, 1000]
            posY = (1..20).random().toFloat() * 50 // Случайное значение Y в диапазоне [50, 1000]

            // 50% шанс на выигрыш
            val chanceToWin = (0..1).random()
            if (chanceToWin == 1) {
                // Start the WinActivity
                val intent = Intent(context, WinActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}
