package gr.codehub.sacchon.representations.forms;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Data
public class GlucoseFormRepresentation {
    private Optional<Integer> id;
    private double glucoseLevel;
    private long dateTime;


    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.dateTime), ZoneId.systemDefault());
    }
}
