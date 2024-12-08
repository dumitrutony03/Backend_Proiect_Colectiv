package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHospitalDto {
    public String doctorUsername;
    public String hospitalName;
}
