package gr.codehub.sacchon.representations;

import lombok.Data;

@Data
public class SingleValueRepresentation<T> {
    T value;


    public SingleValueRepresentation(T value){
        this.value=value;
    }
}
