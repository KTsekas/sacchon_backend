package gr.codehub.sacchon.representations.forms;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
public class PatientCarbFormRepresentation {
    private int id=-1;
    private double carbIntake;
    private String date;

    public LocalDate getLocalDate(){
        try{
            return LocalDate.parse(this.date, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        catch(DateTimeParseException ex){
            return null;
        }
    }
}
