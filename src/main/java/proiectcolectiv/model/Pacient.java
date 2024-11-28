package proiectcolectiv.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("pacient")
public class Pacient extends UserData{

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
