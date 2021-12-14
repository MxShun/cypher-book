package cypher.book.domain.error

/**
 * Data が見つからなかったときの例外
 */
class DataNotFountException(
    override val message: String,
) : Exception(message)
