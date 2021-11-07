package cypher.book.application.usecase

import cypher.book.application.repository.BookRepository
import cypher.book.domain.entity.Book
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
     * isbn が未指定 null の場合は一覧を取得する
     *
     * @return List<Book> 検索結果本リスト
     */
    fun fetchBy(isbn: String?): List<Book> {
        if (isbn == null) {
            return fetchAll()
        }

        return bookRepository.fetchBy(isbn = isbn)
    }
}
