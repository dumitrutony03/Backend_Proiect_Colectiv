package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("doctors")
public class Doctors extends UserData {
    public List<Hospitals> hospitals;
    public String speciality;
    public List<Reviews> reviews;
    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + this.id +
                ", hospitals=" + hospitals +
                ", speciality='" + speciality + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}
