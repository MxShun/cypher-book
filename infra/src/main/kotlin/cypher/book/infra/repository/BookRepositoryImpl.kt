package cypher.book.infra.repository

import cypher.book.application.repository.BookRepository
import cypher.book.infra.entity.Book
import cypher.book.infra.mapper.BookMapper
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(private val bookMapper: BookMapper) : BookRepository {
    /**
     * 本一覧取得
     *
     * @return List<cypher.book.domain.entity.Book> 本一覧リスト
     */
    override fun fetchAll(): List<cypher.book.domain.entity.Book> {
        val books: List<Book> = bookMapper.selectAll()

        return books.map { it.toEntity() }
    }

    /**
     * 本を isbn で検索する
     *
     * @param String isbn 国際標準図書番号
     *
     * @return cypher.book.domain.entity.Book? 検索結果の本
     */
    override fun fetchBy(isbn: String): cypher.book.domain.entity.Book? {
        val book: Book? = bookMapper.selectBy(isbn = isbn)

        return book?.toEntity()
    }

    /**
     * DB とのコミュニケーション DTO を Book エンティティに変換するヘルパー関数
     *
     * @receiver Book DB とのコミュニケーション DTO
     *
     * @return cypher.book.domain.entity.Book 本のエンティティ
     */
    private fun Book.toEntity() = cypher.book.domain.entity.Book(
        isbn = isbn,
        title = title,
        author = author,
        publisher = publisher,
        price = price,
    )
}
