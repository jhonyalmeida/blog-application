package blog.app;

import java.util.List;

import javax.inject.Singleton;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
@Validated
public class BlogRepository implements MongoReactiveRepository<Blog> {

    private final MongoClient mongoClient;

    public BlogRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<List<Blog>> findByUser(String userId) {
        return Flowable.fromPublisher(
            getCollection()
                .find(Filters.eq("userId", userId))
                .sort(Sorts.descending("lastPublished"))
        ).toList();
    }

    public MongoCollection<Blog> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("blog", Blog.class);
    }

}