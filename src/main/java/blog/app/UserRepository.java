package blog.app;

import javax.inject.Singleton;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Singleton
public class UserRepository implements MongoReactiveRepository<User> {

    private final MongoClient mongoClient;

    public UserRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Maybe<User> findByLoginAndPassword(User credentials) {
        return Flowable.fromPublisher(
            getCollection()
                .find(Filters.and(
                    Filters.eq("login", credentials.getLogin()),
                    Filters.eq("password", credentials.getPassword())
                )).limit(1)
        ).firstElement();
    }

    public MongoCollection<User> getCollection() {
        return mongoClient
                .getDatabase("blogs")
                .getCollection("users", User.class);
    }

}