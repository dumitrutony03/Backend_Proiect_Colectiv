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
public class Doctors extends UserData {
    public List<Pacient> pacients;
    public List<Hospitals> hospitals;
    public String speciality;
    public float rating;

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + this.id +
                ", pacients=" + pacients +
                ", hospitals=" + hospitals +
                ", speciality='" + speciality + '\'' +
                ", rating=" + rating +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
