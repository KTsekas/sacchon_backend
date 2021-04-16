package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.CarbRecord;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientCarbRepresentation {

    private int id;
    private double glucoseLevel;
    private LocalDate date;

    public PatientCarbRepresentation(CarbRecord record){
        if( record == null)
            return;
        this.id = record.getId();
        this.glucoseLevel=record.getCarbIntake();
        this.date= record.getDate();
    }
}

