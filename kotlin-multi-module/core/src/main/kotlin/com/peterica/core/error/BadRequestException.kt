package com.peterica.core.error

data class BadRequestException(
    override val message: String
) : Exception(message)
