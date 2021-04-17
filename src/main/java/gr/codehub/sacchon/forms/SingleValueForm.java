package gr.codehub.sacchon.forms;

import lombok.Data;

@Data
public class SingleValueForm<T> {
    T value;
}
