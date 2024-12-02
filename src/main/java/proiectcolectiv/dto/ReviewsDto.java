package proiectcolectiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDto {
    public int id;
    public String review_text;
    public float rating;
    //comentariu Test
}
