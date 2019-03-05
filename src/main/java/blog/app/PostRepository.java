package blog.app;

import java.util.List;

import javax.inject.Singleton;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import org.bson.types.ObjectId;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class PostRepository implements MongoReactiveRepository<Post> {

    private final MongoClient mongoClient;

    public PostRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<List<Post>> findByBlogId(ObjectId blogId) {
        return Flowable.fromPublisher(
            getCollection()
                .find(Filters.eq("blog.id", blogId))
        ).toList();
    }

    public MongoCollection<Post> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("posts", Post.class);
    }

}