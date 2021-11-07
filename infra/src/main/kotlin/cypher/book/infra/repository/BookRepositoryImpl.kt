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
     *
     * @throws DataAccessException
     */
    override fun fetchAll(): List<cypher.book.domain.entity.Book> {
        val books: List<Book> = bookMapper.selectAll()

        return books.toEntities()
    }

    /**
     * 本を isbn で検索する
     *
     * @param isbn 国際標準図書番号
     *
     * @return List<cypher.book.domain.entity.Book> 検索結果本リスト
     *
     * @throws DataAccessException
     */
    override fun fetchBy(isbn: String): List<cypher.book.domain.entity.Book> {
        val books: List<Book> = bookMapper.selectBy(isbn = isbn)

        return books.toEntities()
    }

    /**
     * DB とのコミュニケーション DTO を Book エンティティに変換するヘルパー関数
     *
     * @return List<cypher.book.domain.entity.Book> 本のエンティティ
     */
    private fun List<Book>.toEntities() = map {
        cypher.book.domain.entity.Book(
            isbn = it.isbn,
            title = it.title,
            author = it.author,
            publisher = it.publisher,
            price = it.price,
        )
    }
}
