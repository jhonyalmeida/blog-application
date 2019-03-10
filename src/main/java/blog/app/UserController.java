package blog.app;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.mongodb.client.result.DeleteResult;

import org.bson.types.ObjectId;

@Controller("/users")
public class UserController {

    @Inject
    private UserRepository userRepository;

    @Get("/{id}")
    public Maybe<User> getOne(String id) {
        return userRepository.findOne(new ObjectId(id));
    }

    @Post
    public Single<User> create(@Body @Valid User user) {
        return userRepository.create(user);
    }

    @Put("/{id}")
    public Single<User> update(@Body User user, String id) {
        return userRepository.update(new ObjectId(id), user);
    }

    @Delete("/{id}")
    public Single<User> delete(String id) {
        return userRepository.delete(new ObjectId(id));
    }

    @Get("/clear")
    public Single<List<DeleteResult>> clear() {
        return userRepository.clear();
    }

    @Post("/login")
    public Maybe<User> login(@Body User user) {
        return userRepository.findByLoginAndPassword(user);
    }

}