package com.assignment.openweatherapp.domain.models

enum class HttpStatus(val code: Int) {
    OK(200),
    NOT_FOUND(404);

    companion object {
        fun fromCode(code: Int): HttpStatus? {
            return entries.find { it.code == code }
        }
    }
}