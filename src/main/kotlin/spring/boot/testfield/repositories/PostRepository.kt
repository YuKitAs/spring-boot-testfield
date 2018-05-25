package spring.boot.testfield.repositories

import org.springframework.data.jpa.repository.JpaRepository
import spring.boot.testfield.models.Post

interface PostRepository : JpaRepository<Post, Long>