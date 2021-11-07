package cypher.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["cypher.book"])
class BookApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<BookApplication>(*args)
}
