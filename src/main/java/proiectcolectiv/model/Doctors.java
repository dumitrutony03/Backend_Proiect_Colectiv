package proiectcolectiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("doctors")
public class Doctors extends UserData {
    public List<String> hospitals;
    public String speciality;
    public List<Reviews> reviews;

    public void addHosptial(String hospitalName) {
        if (hospitals == null) {
            hospitals = new ArrayList<>();
        }
        this.hospitals.add(hospitalName);
    }

    public void removeHospital(String hospitalName) {
        if (hospitals == null) {
            hospitals = new ArrayList<>();
        }
        this.hospitals.remove(hospitalName);
    }

    public void addReview(Reviews review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        this.reviews.add(review);
    }

}
