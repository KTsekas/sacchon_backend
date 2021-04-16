package gr.codehub.sacchon.representations;

import lombok.Data;

@Data
public class AvgRepresentation {
    double value;

    public AvgRepresentation(double value) {
        this.value = value;
    }
}
