package cypher.book.infra.mapper

import cypher.book.infra.entity.Book
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
     *
     * @throws DataAccessException
     */
    @Select("""
        SELECT
            isbn,
            title,
            author,
            publisher,
            price
        FROM
            book
    """)
    fun selectAll(): List<Book>

    /**
     * 本を isbn で検索する
     *
     * @param String isbn 国際標準図書番号
     *
     * @return Book? 検索結果の本
     *
     * @throws DataAccessException
     */
    @Select("""
        SELECT
            isbn,
            title,
            author,
            publisher,
            price
        FROM
            book
        WHERE
            isbn = #{isbn}
    """)
    fun selectBy(isbn: String): Book?
}
