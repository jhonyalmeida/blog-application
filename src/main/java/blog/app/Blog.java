package blog.app;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

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
public class Blog {

    @JsonIgnore
    protected ObjectId id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastPublished;

    @JsonProperty("_id")
    public String getStringId() {
        return id != null ? id.toHexString() : null;
    }

}