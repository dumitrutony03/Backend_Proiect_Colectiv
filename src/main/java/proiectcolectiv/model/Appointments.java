package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("appointments")
public class Appointments {
    @Id
    @Indexed(unique = true)
    public int id;
    public String doctorUsername;
    public String pacientUsername;
    public String date;//Constructs a Date object initialized with the given year, month, and day.
    public String begin;//Constructs a LocalTime object initialized with the given values for the hour, minute, and second. The driver sets the date components to January 1, 1970. Any method that attempts to access the date components of a Time object will throw a java.lang.IllegalArgumentException.
    public String end;//same
}
