package gr.codehub.sacchon.representations.patient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FieldCountRepresentation {
    int carbs;
    int glucose;


    public FieldCountRepresentation(int carbs,int glucose) {
        this.carbs=carbs;
        this.glucose=glucose;
    }
}
