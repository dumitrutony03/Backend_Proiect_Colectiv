package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("hospitals")
public class Hospitals implements Serializable {
    @Id
    @Indexed(unique = true)
    public int id;
    public String name;
    public String adress;
    public float latitude;
    public float longitude;
    public List<Reviews> reviews;
    @Override
    public String toString() {
        return "Hospitals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
