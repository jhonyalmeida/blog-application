package blog.app;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonPropertyOrder({ "_id" })

@Data
@EqualsAndHashCode
public class Post {
 
    @JsonIgnore
    protected ObjectId id;
    private Blog blog;
    private Section rootSection;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonProperty("_id")
    public String getStringId() {
        return id != null ? id.toHexString() : null;
    }
 
}