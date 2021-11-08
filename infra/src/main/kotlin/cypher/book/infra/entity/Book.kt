package cypher.book.infra.entity

/**
 * 本の DB コミュニケーション DTO
 *
 * @param isbn 国際標準図書番号 書籍出版物を一意に識別するユニークキー
 * @param title 題
 * @param author 著者
 * @param publisher 出版者
 * @param price 価格
 */
data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
)
