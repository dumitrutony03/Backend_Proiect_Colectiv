package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("appointments")
public class Appointments {
    @Id
    @Indexed(unique = true)
    int id;
    public ArrayList<Doctors> doctors;
    public ArrayList<Pacient> pacients;
    public Date date;//Constructs a Date object initialized with the given year, month, and day.
    public Time time;//Constructs a Time object initialized with the given values for the hour, minute, and second. The driver sets the date components to January 1, 1970. Any method that attempts to access the date components of a Time object will throw a java.lang.IllegalArgumentException.
    public String diagnosis;

    @Override
    public String toString() {
        return "Appointments{" +
                "id=" + id +
                ", doctors=" + doctors +
                ", pacients=" + pacients +
                ", date=" + date +
                ", time=" + time +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
