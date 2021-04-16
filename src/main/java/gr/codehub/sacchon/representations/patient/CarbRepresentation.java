package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.CarbRecord;
import lombok.Data;

@Data
public class CarbRepresentation {

    private int id;
    private double glucoseLevel;
    private String date;

    public CarbRepresentation(CarbRecord record){
        if( record == null)
            return;
        this.id = record.getId();
        this.glucoseLevel=record.getCarbIntake();
        this.date=record.getDateCreated().toString();
    }
}

