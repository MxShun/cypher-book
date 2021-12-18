package cypher.book.application.usecase

import cypher.book.application.repository.BookRepository
import cypher.book.domain.entity.Book
import cypher.book.domain.entity.ISBN
import cypher.book.domain.error.DataNotFountException
import org.springframework.stereotype.Component

@Component
class FetchBook(private val bookRepository: BookRepository) {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
     */
    fun fetchAll(): List<Book> = bookRepository.fetchAll()

    /**
     * 本を isbn で検索する
     *
     * @param ISBN isbn 国際標準図書番号
     *
     * @return Book 検索結果の本
     *
     * @throws DataNotFountException
     */
    fun fetchBy(isbn: ISBN) = bookRepository.fetchBy(isbn = isbn) ?: throw DataNotFountException(message = "No Book found. isbn=$isbn")
}
