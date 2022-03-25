package com.warh.viewmodel_practice01.repository

import com.warh.viewmodel_practice01.model.QuoteModel

class QuoteProvider {
    companion object{
        private val quote = listOf(
            QuoteModel(0, "No hay que ir para atrás ni para darse impulso", "Lao Tsé"),
            QuoteModel(1, "No hay caminos para la paz; la paz es el camino", "Mahatma Gandhi"),
            QuoteModel(2, "Aprende a vivir y sabrás morir bien", "Confucio"),
            QuoteModel(3, "Cada día sabemos más y entendemos menos", "Albert Einstein"),
            QuoteModel(4, "El sabio no dice nunca todo lo que piensa, pero siempre piensa todo lo que dice", "Aristóteles"),
            QuoteModel(5, "Lo que no te mata, te hace más fuerte", "Friedrich Nietzsche"),
            QuoteModel(6, "La peor experiencia es la mejor maestra", "Kovo"),
            QuoteModel(7, "Solo sé que no sé nada", "Sócrates"),
            QuoteModel(8, "Cuanto más grande es la prueba, más glorioso es el triunfo", "William Shakespeare"),
            QuoteModel(9, "Sólo la propia y personal experiencia hace al hombre sabio", "Sigmund Freud"),
        )

        fun getRandomQuote(): QuoteModel {
            val position = (0..9).random()
            return quote[position]
        }
    }
}