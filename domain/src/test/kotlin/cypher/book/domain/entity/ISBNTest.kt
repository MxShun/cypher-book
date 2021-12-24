package cypher.book.domain.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class ISBNTest {
    @Nested
    @DisplayName("validateで桁数を検証できること")
    inner class Validate {
        @Test
        @DisplayName("validateでISBNの有効な文字列の桁数が10桁の場合はISBN-10として例外をスローしないこと")
        fun validateISBN10Length() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN(value = "ISBN4-7897-1537-5")
            }
        }

        @Test
        @DisplayName("validateでISBNの有効な文字列の桁数が13桁の場合はISBN-13として例外をスローしないこと")
        fun validateISBN13Length() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN(value = "ISBN978-3-16-148410-0")
            }
        }

        @Test
        @DisplayName("validateでISBNの有効な文字列の桁数が不正な場合は例外をスローすること")
        fun validateInvalidISBNLength() {
            assertThrows<IllegalArgumentException>("想定した例外のスローに失敗") {
                ISBN(value = "999")
            }
        }
    }

    @Nested
    @DisplayName("checkChecksumISBN10でISBN-10のチェックディジット計算検証ができること")
    inner class CheckChecksumISBN10 {
        @Test
        @DisplayName("checkChecksumISBN10でISBN-10の値が有効な場合は例外をスローしないこと")
        fun checkChecksumValidISBN10() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN("ISBN4-7897-1537-5")
            }
        }

        @Test
        @DisplayName("checkChecksumISBN10でISBN-10の値が不正な場合は例外をスローしないこと")
        fun checkChecksumInvalidISBN10() {
            assertThrows<IllegalArgumentException>("想定しない例外スロー") {
                ISBN("9-99-9999999")
            }
        }
    }

    @Nested
    @DisplayName("CheckFormatISBN10でISBN-10のフォーマット検証ができること")
    inner class CheckFormatISBN10 {
        @Test
        @DisplayName("checkChecksumISBN10でISBN-10のフォーマットが有効な場合は例外をスローしないこと")
        fun checkFormatISBN10() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN("ISBN4-7897-1537-5")
            }
        }

        @Test
        @DisplayName("checkChecksumISBN10でISBN-10のフォーマットが不正な場合は例外をスローしないこと")
        fun checkFormatInvalidISBN10() {
            assertThrows<IllegalArgumentException>("想定しない例外スロー") {
                ISBN("9-99-9999999")
            }
        }
    }

    @Nested
    @DisplayName("checkChecksumISBN13でISBN-13のチェックディジット計算検証ができること")
    inner class CheckChecksumISBN13 {
        @Test
        @DisplayName("checkChecksumISBN13でISBN-13の値が有効な場合は例外をスローしないこと")
        fun checkChecksumValidISBN13() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN("ISBN978-3-16-148410-0")
            }
        }

        @Test
        @DisplayName("checkChecksumISBN13でISBN-13の値が不正な場合は例外をスローしないこと")
        fun checkChecksumInvalidISBN13() {
            assertThrows<IllegalArgumentException>("想定しない例外スロー") {
                ISBN("999-9-99-999999-9")
            }
        }
    }

    @Nested
    @DisplayName("CheckFormatISBN13でISBN-13のフォーマット検証ができること")
    inner class CheckFormatISBN13 {
        @Test
        @DisplayName("checkChecksumISBN13でISBN-10のフォーマットが有効な場合は例外をスローしないこと")
        fun checkFormatISBN13() {
            assertDoesNotThrow("想定しない例外スロー") {
                ISBN("ISBN978-3-16-148410-0")
            }
        }

        @Test
        @DisplayName("checkChecksumISBN13でISBN-10のフォーマットが不正な場合は例外をスローしないこと")
        fun checkFormatInvalidISBN13() {
            assertThrows<IllegalArgumentException>("想定しない例外スロー") {
                ISBN("999-9-99-999999-9")
            }
        }
    }

    @Test
    @DisplayName("digitsでISBNとして有効な文字列が取得できること")
    fun digits() {
        assertEquals(
            "9783161484100",
            ISBN(value = "ISBN978-3-16-148410-0").digits(),
            "想定した値の取得に失敗"
        )
    }

    @Test
    @DisplayName("digitsWithHyphenでハイフンを含むISBNとして有効な文字列が取得できること")
    fun digitsWithHyphen() {
        assertEquals(
            "978-3-16-148410-0",
            ISBN(value = "ISBN978-3-16-148410-0").digitsWithHyphen(),
            "想定した値の取得に失敗"
        )
    }
}
