package cypher.book.web.controller

import cypher.book.reposiotry.BookRepository
import cypher.book.web.entity.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookRepository: BookRepository) {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
     */
    @GetMapping("/list")
    fun list(): List<Book> = bookRepository.selectAll()

    /**
     * 本を isbn で検索する
     *
     * @return List<Book> 検索結果本リスト
     */
    @GetMapping("/search")
    fun search(@RequestParam(required = false) isbn: String?): List<Book> = bookRepository.selectBy(isbn = isbn)
}
