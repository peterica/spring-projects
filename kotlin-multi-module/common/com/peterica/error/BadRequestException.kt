package com.peterica.error

data class BadRequestException(
    override val message: String
) : Exception(message)
