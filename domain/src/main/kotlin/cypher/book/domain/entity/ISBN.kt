package cypher.book.domain.entity

import com.google.common.annotations.VisibleForTesting

/**
 * ISBN の value object
 *
 * @property value 国際標準図書番号 書籍出版物を一意に識別するユニークキー
 */
@JvmInline
value class ISBN(private val value: String) {
    init {
        validate()
    }

    /**
     * ISBN の文字列が有効かどうかを検証する
     * 桁数は 10 桁（ISBN-10）もしくは 13 桁（ISBN-13）が有効
     *
     * @throws IllegalArgumentException
     */
    @VisibleForTesting
    fun validate() {
        when (digits().length) {
            10 -> checkChecksumISBN10()
            13 -> checkChecksumISBN13()
            else -> throw IllegalArgumentException("Invalid/unsupported isbn value length")
        }
    }

    /**
     * ISBN-10 のチェックディジット計算
     *
     * @throws IllegalArgumentException
     */
    @VisibleForTesting
    fun checkChecksumISBN10() {
        val sum = digits().withIndex().sumOf { char ->
            (char.value - '0') * (10 - char.index)
        } + if (digits()[9] == 'X') 10 else digits()[9] - '0'

        if (sum % 11 != 0) throw IllegalArgumentException("Invalid/unsupported isbn value")
    }

    /**
     * ISBN-13 のチェックディジット計算
     *
     * @throws IllegalArgumentException
     */
    @VisibleForTesting
    fun checkChecksumISBN13() {
        val sum = digits().withIndex().sumOf { char ->
            (char.value - '0') * if (char.index % 2 == 0) 1 else 3
        }

        if (sum % 10 != 0) throw IllegalArgumentException("Invalid/unsupported isbn value")
    }

    /**
     * ISBN として有効な文字列を返す
     * ISBN は数字もしくは'X'を文字列として含む
     *
     * @return String digits ISBN として有効な文字列
     */
    fun digits(): String = value.replace(NOT_DIGITS_OR_NOT_X, "")

    companion object {
        private val NOT_DIGITS_OR_NOT_X = Regex("[^\\dX]")
    }
}
