package proiectcolectiv.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("pacient")
public class Pacient extends UserData{
    public List<String> diagnostics;
}
