package blog.app;

import java.util.List;

import javax.inject.Inject;

import com.mongodb.client.result.DeleteResult;

import org.bson.types.ObjectId;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Controller("/posts")
public class PostController {

    @Inject
    private PostRepository postRepository;

    @Get
    public Single<List<Post>> getList() {
        return postRepository.findAll();
    }

    @Get("/{id}")
    public Maybe<Post> getOne(String id) {
        return postRepository.findOne(new ObjectId(id));
    }

    @io.micronaut.http.annotation.Post
    public Single<Post> create(@Body Post post) {
        return postRepository.create(post);
    }

    @Put("/{id}")
    public Single<Post> update(@Body Post post, String id) {
        return postRepository.update(new ObjectId(id), post);
    }

    @Delete("/{id}")
    public Single<Post> delete(String id) {
        return postRepository.delete(new ObjectId(id));
    }

    @Get("/clear")
    public Single<List<DeleteResult>> clear() {
        return postRepository.clear();
    }

}