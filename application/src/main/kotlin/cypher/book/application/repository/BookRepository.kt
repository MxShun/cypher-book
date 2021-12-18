package cypher.book.application.repository

import cypher.book.domain.entity.Book
import cypher.book.domain.entity.ISBN

interface BookRepository {
    /**
     * 本一覧取得
     *
     * @return List<Book> 本一覧リスト
     */
    fun fetchAll(): List<Book>

    /**
     * 本を isbn で検索する
     *
     * @param ISBN isbn 国際標準図書番号
     *
     * @return Book? 検索結果本リスト
     */
    fun fetchBy(isbn: ISBN): Book?
}
