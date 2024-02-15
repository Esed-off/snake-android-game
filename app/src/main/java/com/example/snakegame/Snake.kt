package com.example.snakegame

class Snake {
    companion object {
        // Начальные координаты головы змейки
        var headX = 0f
        var headY = 0f

        // Список сегментов тела змейки (по умолчанию один сегмент)
        var bodyParts = mutableListOf(arrayOf(0f, 0f))

        // Направление движения змейки
        var direction = "right"

        // Флаг живой/мертвой змейки
        var alive = false

        // Проверка возможности движения змейки
        fun possibleMove(): Boolean {
            if (headX < 0f || headX > 1000f || headY < 0f || headY > 1000)
                return false
            return true
        }

        // Проверка на столкновение с собственным телом
        fun checkCollision(): Boolean {
            for (i in 1 until bodyParts.size) {
                if (headX == bodyParts[i][0] && headY == bodyParts[i][1]) {
                    return true
                }
            }
            return false
        }

        // Сброс состояния змейки
        fun reset() {
            headX = 0f
            headY = 0f
            bodyParts = mutableListOf(arrayOf(0f, 0f))
            direction = "right"
        }
    }
}
