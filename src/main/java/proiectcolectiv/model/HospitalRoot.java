package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRoot implements Serializable {
    //clasa ce salveaza spitalele din json
    public ArrayList<Hospitals> hospitals;
}
