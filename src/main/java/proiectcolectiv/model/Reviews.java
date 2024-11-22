package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("reviews")
public class Reviews {
    @Id
    @Indexed(unique = true)
    public int id;
    public String review_text;
    public float rating;

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", review_text='" + review_text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
