package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalsDto {
    public int id;
    public String name;
    public String adress;
    public float latitude;
    public float longitude;
}
