package spring.boot.testfield.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spring.boot.testfield.models.Post
import spring.boot.testfield.repositories.PostRepository
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostController(val postRepository: PostRepository) {
    @GetMapping("/posts")
    fun getAllPosts() = postRepository.findAll()

    @GetMapping("/posts/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<Post> =
            postRepository.findById(id).map { post -> ResponseEntity.ok(post) }.orElse(ResponseEntity.notFound().build())

    @PostMapping("/posts")
    fun createPost(@Valid @RequestBody post: Post): Post = postRepository.save(post)

    @PutMapping("/posts/{id}")
    fun updatePostById(@PathVariable id: Long, @Valid @RequestBody newPost: Post): ResponseEntity<Post> =
            postRepository.findById(id).map { post ->
                val updatedPost: Post = post.copy(title = newPost.title, content = newPost.content)
                ResponseEntity.ok().body(postRepository.save(updatedPost))
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: Long) = postRepository.findById(id).map { post ->
        postRepository.delete(post)
        ResponseEntity<Void>(HttpStatus.OK)
    }.orElse(ResponseEntity.notFound().build())
}
