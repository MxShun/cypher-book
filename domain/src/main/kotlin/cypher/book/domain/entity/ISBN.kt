package cypher.book.domain.entity

import com.google.common.annotations.VisibleForTesting

/**
 * ISBN の value object
 *
 * @property value 国際標準図書番号 書籍出版物を一意に識別するユニークキー
 */
@JvmInline
@Suppress("MagicNumber")
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
            10 -> {
                checkChecksumISBN10()
                checkFormatISBN10()
            }
            13 -> {
                checkChecksumISBN13()
                checkFormatISBN13()
            }
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

        require(sum % 11 == 0) { "Invalid/unsupported isbn value" }
    }

    /**
     * ISBN-10 のフォーマット検査
     *
     * @throws IllegalArgumentException
     */
    @VisibleForTesting
    fun checkFormatISBN10() {
        require(ISBN10_FORMAT.matches(digitsWithHyphen())) { "Invalid/unsupported isbn format" }
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

        require(sum % 10 == 0) { "Invalid/unsupported isbn value" }
    }

    /**
     * ISBN-13 のフォーマット検査
     *
     * @throws IllegalArgumentException
     */
    @VisibleForTesting
    fun checkFormatISBN13() {
        require(ISBN13_FORMAT.matches(digitsWithHyphen())) { "Invalid/unsupported isbn format" }
    }

    /**
     * ISBN として有効な文字列を返す
     * ISBN は数字もしくは'X'を文字列として含む
     *
     * @return String digits ISBN として有効な文字列
     */
    fun digits(): String = value.replace(NOT_DIGITS_OR_NOT_X, "")

    /**
     * ハイフンを含む ISBN として有効な文字列を返す
     *
     * @return String digits ハイフンを含む ISBN として有効な文字列
     */
    @VisibleForTesting
    fun digitsWithHyphen(): String = value.replace(NOT_DIGITS_OR_NOT_X_OR_NOT_HYPHEN, "")

    companion object {
        private val NOT_DIGITS_OR_NOT_X = Regex("[^\\dX]")
        private val NOT_DIGITS_OR_NOT_X_OR_NOT_HYPHEN = Regex("[^\\dX-]")
        private val ISBN10_FORMAT = Regex("^((?=.{11}([-])[\\dxX]\$)(?:\\d+\\2){3}[\\dxX])")
        private val ISBN13_FORMAT = Regex("^(?:97[89])?([-]?)(?=(?:\\d\\1?){9}\\1[\\dxX]\$)(?:\\d+\\1){3}[\\dxX]")
    }
}
