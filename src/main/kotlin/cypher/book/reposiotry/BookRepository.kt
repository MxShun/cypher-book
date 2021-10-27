package cypher.book.reposiotry

import cypher.book.web.entity.Book
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookRepository(private val jdbcTemplate: JdbcTemplate) {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
     *
     * @throws DataAccessException
     */
    fun selectAll(): List<Book> = jdbcTemplate.query(
        """
            SELECT
                isbn,
                title,
                author,
                publisher,
                price
            FROM
                book
        """,
        DataClassRowMapper(Book::class.java),
    )

    /**
     * 本を isbn で検索する
     * isbn が未指定 null の場合は一覧を取得する
     * NOTE: 本当は title、author、publisher を自由に組み合わせて絞り込み検索できるようにしたい
     *
     * @param isbn 国際標準図書番号
     *
     * @return List<Book> 検索結果本リスト
     *
     * @throws DataAccessException
     */
    fun selectBy(isbn: String?): List<Book> {
        if (isbn == null) {
            return selectAll()
        }

        return jdbcTemplate.query(
            """
                SELECT
                    isbn,
                    title,
                    author,
                    publisher,
                    price
                FROM
                    book
                WHERE
                    isbn = ?
            """,
            DataClassRowMapper(Book::class.java),
            isbn
        )
    }
}
