package blog.app;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Section {
 
    private ObjectId id;
    private String title;
    private String content;
    private List<Section> sections;

    public Section(String title, String content) {
        this.title = title;
        this.content = content;
    }
 
}