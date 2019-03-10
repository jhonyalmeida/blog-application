package blog.app;

import java.util.List;

import javax.validation.Valid;

import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;

import org.bson.Document;
import org.bson.conversions.Bson;
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

    default Single<T> create(@Valid T object) {
        return Single.fromPublisher(
            getCollection().insertOne(object)
        )
        .flatMap(success -> onCreateSuccess(success, object));
    }

    default Single<T> onCreateSuccess(Success success, T object) {
        return Single.just(object);
    }

    default Single<T> update(ObjectId id, @Valid T object) {
        return Single.fromPublisher(
            getCollection().findOneAndReplace(Filters.eq("_id", id), object)
        ).map(success -> object);
    }

    default Single<UpdateResult> patch(ObjectId id, Bson patch) {
        return Single.fromPublisher(
            getCollection().updateOne(Filters.eq("_id", id), new Document("$set", patch))
        );
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