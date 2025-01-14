package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewDto {
    public String name;
    public float rating;
    public String review_text;
}
