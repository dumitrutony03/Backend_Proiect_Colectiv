package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsDto {
    public int id;
    public ArrayList<DoctorsDto> doctors;
    public ArrayList<PacientDto> pacients;
    public Date date;//Constructs a Date object initialized with the given year, month, and day.
    public Time time;//Constructs a Time object initialized with the given values for the hour, minute, and second. The driver sets the date components to January 1, 1970. Any method that attempts to access the date components of a Time object will throw a java.lang.IllegalArgumentException.
    public String diagnosis;
}
