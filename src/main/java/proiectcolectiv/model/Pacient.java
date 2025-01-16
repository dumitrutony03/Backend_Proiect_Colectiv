package proiectcolectiv.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("pacient")
public class Pacient extends UserData{
    public List<String> diagnostics;

    public void addDiagnostic(String diagnostic) {
        if (diagnostics == null) {
            diagnostics = new ArrayList<>();
        }
        diagnostics.add(diagnostic);
    }


}
