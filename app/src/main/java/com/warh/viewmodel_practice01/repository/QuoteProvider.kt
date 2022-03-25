package com.warh.viewmodel_practice01.repository

import com.warh.viewmodel_practice01.model.QuoteModel

class QuoteProvider {
    companion object{
        private val quote = listOf(
            QuoteModel("No hay que ir para atrás ni para darse impulso", "Lao Tsé"),
            QuoteModel("No hay caminos para la paz; la paz es el camino", "Mahatma Gandhi"),
            QuoteModel("Aprende a vivir y sabrás morir bien", "Confucio"),
            QuoteModel("Cada día sabemos más y entendemos menos", "Albert Einstein"),
            QuoteModel("El sabio no dice nunca todo lo que piensa, pero siempre piensa todo lo que dice", "Aristóteles"),
            QuoteModel("Lo que no te mata, te hace más fuerte", "Friedrich Nietzsche"),
            QuoteModel("La peor experiencia es la mejor maestra", "Kovo"),
            QuoteModel("Solo sé que no sé nada", "Sócrates"),
            QuoteModel("Cuanto más grande es la prueba, más glorioso es el triunfo", "William Shakespeare"),
            QuoteModel("Sólo la propia y personal experiencia hace al hombre sabio", "Sigmund Freud"),
        )

        fun getRandomQuote(): QuoteModel {
            val position = (0..9).random()
            return quote[position]
        }
    }
}