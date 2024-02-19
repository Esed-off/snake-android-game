package com.example.snakegame

import android.widget.Toast

class Food {
    companion object {
        var posX = 500f // Начальная позиция X
        var posY = 500f // Начальная позиция Y

        fun generate() {
            // Генерация новой позиции
            posX = (1..20).random().toFloat() * 50 // Случайное значение X в диапазоне [50, 1000]
            posY = (1..20).random().toFloat() * 50 // Случайное значение Y в диапазоне [50, 1000]

            // 50% шанс на выигрыш
            val chanceToWin = (0..1).random()
            if (chanceToWin == 1) {
                // Показать сообщение о выигрыше (здесь используется Toast, можно заменить на свой механизм)
                showToast("Congratulations! You have won!")
            }
        }

        // Метод для отображения всплывающего сообщения (Toast)
        private fun showToast(message: String) {
            // Здесь можно использовать свой механизм отображения сообщения
            // Например, для использования Toast вам нужен контекст (Context)
            // Ниже пример, как использовать Toast:
            // Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
