package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsDto {
    public int id;
    public String doctorUsername;
    public String pacientUsername;
    public String date;//Constructs a Date object initialized with the given year, month, and day.
    public String begin;//Constructs a LocalTime object initialized with the given values for the hour, minute, and second. The driver sets the date components to January 1, 1970. Any method that attempts to access the date components of a Time object will throw a java.lang.IllegalArgumentException.
    public String end;//same
    //TODO
    //public HospitalsDto hospitalsDto;
}
