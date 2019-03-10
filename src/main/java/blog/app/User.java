package blog.app;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonPropertyOrder({ "_id" })

@Data
@EqualsAndHashCode
public class User {
 
    @JsonIgnore
    protected ObjectId id;

    @NotNull
    private String login;
    
    @NotNull
    private String password;

    @JsonProperty("_id")
    public String getStringId() {
        return id != null ? id.toHexString() : null;
    }
 
}