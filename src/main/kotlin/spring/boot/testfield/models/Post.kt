package spring.boot.testfield.models

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity
data class Post(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        val title: String = "",

        val content: String = "",

        val author: String = "",

        val createdAt: LocalDate
)