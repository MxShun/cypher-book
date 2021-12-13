package cypher.book.domain.error

/**
 * Data が見つからなかったときの例外
 */
class DataNotFountException(
    message: String,
) : Exception(message)
