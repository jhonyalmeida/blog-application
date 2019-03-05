package blog.app;

import javax.inject.Singleton;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

@Singleton
public class UserRepository implements MongoReactiveRepository<User> {

    private final MongoClient mongoClient;

    public UserRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoCollection<User> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("users", User.class);
    }

}