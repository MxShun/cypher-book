package cypher.book.web.controller

import cypher.book.application.usecase.FetchBook
import cypher.book.domain.entity.Book
import cypher.book.domain.entity.ISBN
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

        return books.map { it.toResponse() }
    }

    /**
     * 本を isbn で検索する
     *
     * @param String isbn 国際標準図書番号
     *
     * @return BookResponse 検索結果の本
     */
    @GetMapping("/search")
    fun search(@RequestParam isbn: String): BookResponse {
        // RequestParam に ISBN で受け取ると init が走らないため明示的にインスタンスを作って渡す
        val book: Book = fetchBook.fetchBy(isbn = ISBN(value = isbn))

        return book.toResponse()
    }

    /**
     * Book エンティティをレスポンス DTO に変換するヘルパー関数
     *
     * @receiver Book 本のエンティティ
     *
     * @return BookResponse 本のレスポンス DTO
     */
    private fun Book.toResponse(): BookResponse = BookResponse(
        isbn = isbn.digits(),
        title = title,
        author = author,
        publisher = publisher,
        price = price,
    )
}
