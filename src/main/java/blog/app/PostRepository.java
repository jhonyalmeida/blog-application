package blog.app;

import java.util.List;

import javax.inject.Singleton;
import javax.validation.OverridesAttribute;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;

import org.bson.Document;
import org.bson.types.ObjectId;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class PostRepository implements MongoReactiveRepository<Post> {

    private final MongoClient mongoClient;
    private final BlogRepository blogRepository;

    public PostRepository(MongoClient mongoClient, BlogRepository blogRepository) {
        this.mongoClient = mongoClient;
        this.blogRepository = blogRepository;
    }

    public Single<List<Post>> findByBlogId(String blogId) {
        return Flowable.fromPublisher(
            getCollection()
                .find(Filters.eq("blogId", blogId))
        ).toList();
    }

    @Override
    public Single<Post> onCreateSuccess(Success success, Post post) {
        return blogRepository.patch(
            new ObjectId(post.getBlogId()), 
            new Document("lastPublished", post.getTimestamp())
        ).map(updateResult -> post);
    }

    public MongoCollection<Post> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("posts", Post.class);
    }

}