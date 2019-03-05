package blog.app;

import java.util.List;

import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.MongoCollection;

import org.bson.types.ObjectId;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface MongoReactiveRepository<T> {

    MongoCollection<T> getCollection();

    default Single<List<T>> findAll() {
        return Flowable.fromPublisher(
            getCollection().find()
        ).toList();
    }

    default Maybe<T> findOne(ObjectId id) {
        return Flowable.fromPublisher(
            getCollection()
                .find(Filters.eq("_id", id))
                .limit(1)
        ).firstElement();
    }

    default Single<T> create(T object) {
        return Single.fromPublisher(
            getCollection().insertOne(object)
        ).map(success -> object);
    }

    default Single<T> update(ObjectId id, T object) {
        return Single.fromPublisher(
            getCollection().findOneAndReplace(Filters.eq("_id", id), object)
        ).map(success -> object);
    }

    default Single<T> delete(ObjectId id) {
        return Single.fromPublisher(
            getCollection().findOneAndDelete(Filters.eq("_id", id))
        );
    }

    default Single<List<DeleteResult>> clear() {
        return Flowable.fromPublisher(
            getCollection().deleteMany(Filters.exists("_id"))
        ).toList();
    }

}