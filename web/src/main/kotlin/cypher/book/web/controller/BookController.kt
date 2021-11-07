package cypher.book.web.controller

import cypher.book.application.usecase.FetchBook
import cypher.book.domain.entity.Book
import cypher.book.web.response.BookResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val fetchBook: FetchBook) {
    /**
     * 本一覧取得
     *
     * @return List<BookResponse> 本一覧リスト
     */
    @GetMapping("/list")
    fun list(): List<BookResponse> {
        val books: List<Book> = fetchBook.fetchAll()

        return books.toResponses()
    }

    /**
     * 本を isbn で検索する
     *
     * @return List<BookResponse> 検索結果本リスト
     */
    @GetMapping("/search")
    fun search(@RequestParam(required = false) isbn: String?): List<BookResponse> {
        val books: List<Book> = fetchBook.fetchBy(isbn = isbn)

        return books.toResponses()
    }

    /**
     * Book エンティティをレスポンス DTO に変換するヘルパー関数
     *
     * @return List<BookResponse> 本のレスポンスDTO
     */
    private fun List<Book>.toResponses(): List<BookResponse> = map {
        BookResponse(
            isbn = it.isbn,
            title = it.title,
            author = it.author,
            publisher = it.publisher,
            price = it.price,
        )
    }
}
