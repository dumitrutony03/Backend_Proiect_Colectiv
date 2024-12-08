package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proiectcolectiv.model.Reviews;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalsDto {
    public int id;
    public String name;
    public String adress;
    public float latitude;
    public float longitude;
    public List<ReviewsDto> reviews;
}
