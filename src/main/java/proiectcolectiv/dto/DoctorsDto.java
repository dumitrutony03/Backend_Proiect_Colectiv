package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorsDto {
    public int id;
    public List<PacientDto> pacients;
    public String speciality;
    public float rating;
    public String userName;
    public String password;
    public List<ReviewsDto> reviews;
}
