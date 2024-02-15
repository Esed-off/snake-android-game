package com.example.snakegame

class Food {
    companion object {
        var posX = 500f // Начальная позиция X
        var posY = 500f // Начальная позиция Y

        fun generate() {
            // Генерация новой позиции
            posX = (1..20).random().toFloat() * 50 // Случайное значение X в диапазоне [50, 1000]
            posY = (1..20).random().toFloat() * 50 // Случайное значение Y в диапазоне [50, 1000]
        }
    }
}
