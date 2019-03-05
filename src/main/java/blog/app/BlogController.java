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

@Controller("/blogs")
public class BlogController {

    @Inject
    private BlogRepository blogRepository;
    @Inject
    private PostRepository postRepository;

    @Get
    public Single<List<Blog>> getList() {
        return blogRepository.findAll();
    }

    @Get("/{id}")
    public Maybe<Blog> getOne(String id) {
        return blogRepository.findOne(new ObjectId(id));
    }

    @Get("/{id}/blogs")
    public Single<List<Post>> getPosts(String id) {
        return postRepository.findByBlogId(new ObjectId(id));
    }

    @io.micronaut.http.annotation.Post
    public Single<Blog> create(@Body Blog blog) {
        return blogRepository.create(blog);
    }

    @Put("/{id}")
    public Single<Blog> update(@Body Blog blog, String id) {
        return blogRepository.update(new ObjectId(id), blog);
    }

    @Delete("/{id}")
    public Single<Blog> delete(String id) {
        return blogRepository.delete(new ObjectId(id));
    }

    @Get("/clear")
    public Single<List<DeleteResult>> clear() {
        return blogRepository.clear();
    }

}