package cypher.book.web.advice

import cypher.book.domain.error.DataNotFountException
import cypher.book.web.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse =
        ErrorResponse(reason = "invalid parameter: detail: [${exception.message}]")

    @ExceptionHandler(DataNotFountException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleDataNotFountException(exception: DataNotFountException): ErrorResponse =
        ErrorResponse(reason = exception.message)
}
