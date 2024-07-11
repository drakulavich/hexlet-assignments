package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable int userId) {
        var result = posts.stream()
                .filter(p -> p.getUserId() == userId)
                .toList();
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> create(@PathVariable int userId, @RequestBody Post page) {
        page.setUserId(userId);

        posts.add(page);
        return ResponseEntity.status(HttpStatus.CREATED).body(page);
    }
}
// END
