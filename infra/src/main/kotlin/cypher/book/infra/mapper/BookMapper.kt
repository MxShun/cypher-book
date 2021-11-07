package cypher.book.infra.mapper

import cypher.book.infra.entity.Book
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class BookMapper(private val jdbcTemplate: JdbcTemplate) {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
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
        DataClassRowMapper(Book ::class.java),
    )

    /**
     * 本を isbn で検索する
     * NOTE: 本当は title、author、publisher を自由に組み合わせて絞り込み検索できるようにしたい
     *
     * @param isbn 国際標準図書番号
     *
     * @return List<Book> 検索結果本リスト
     */
    fun selectBy(isbn: String): List<Book> = jdbcTemplate.query(
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
