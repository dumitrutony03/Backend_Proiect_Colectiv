package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("doctors")
public class Doctors {
    @Id
    @Indexed(unique = true)
    int id;
    public List<Pacient> pacients;
    public List<Hospitals> hospitals;
    public String speciality;
    public float rating;
    public String userName;
    public String password;

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", pacients=" + pacients +
                ", hospitals=" + hospitals +
                ", speciality='" + speciality + '\'' +
                ", rating=" + rating +
                '}';
    }
}
