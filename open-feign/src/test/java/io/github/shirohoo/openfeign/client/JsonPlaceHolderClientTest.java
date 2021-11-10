package io.github.shirohoo.openfeign.client;

import static org.assertj.core.api.Assertions.assertThat;
import io.github.shirohoo.openfeign.model.form.Album;
import io.github.shirohoo.openfeign.model.form.Comment;
import io.github.shirohoo.openfeign.model.form.Photo;
import io.github.shirohoo.openfeign.model.form.Post;
import io.github.shirohoo.openfeign.model.form.Todo;
import io.github.shirohoo.openfeign.model.form.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonPlaceHolderClientTest {

    @Autowired
    private JsonPlaceHolderClient client;

    @Test
    void getPosts() throws Exception {
        final List<Post> posts = client.getPosts();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getComments() throws Exception {
        final List<Comment> posts = client.getComment();
        assertThat(posts.size()).isEqualTo(500);
    }

    @Test
    void getAlbums() throws Exception {
        final List<Album> posts = client.getAlbums();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getPhotos() throws Exception {
        final List<Photo> posts = client.getPhotos();
        assertThat(posts.size()).isEqualTo(5000);
    }

    @Test
    void getTodos() throws Exception {
        final List<Todo> posts = client.getTodos();
        assertThat(posts.size()).isEqualTo(200);
    }

    @Test
    void getUsers() throws Exception {
        final List<User> posts = client.getUsers();
        assertThat(posts.size()).isEqualTo(10);
    }

}