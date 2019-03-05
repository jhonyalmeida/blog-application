package blog.app;

import javax.inject.Singleton;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

@Singleton
public class BlogRepository implements MongoReactiveRepository<Blog> {

    private final MongoClient mongoClient;

    public BlogRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoCollection<Blog> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("blog", Blog.class);
    }

}