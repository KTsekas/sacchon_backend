package gr.codehub.sacchon.representations.patient;


import gr.codehub.sacchon.model.CarbRecord;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PatientCarbListRepresentation {
    List<PatientCarbRepresentation> records;
    int count;

    public PatientCarbListRepresentation(List<CarbRecord> list, int count){
        this.count =count;
        if ( list == null )
            this.records = new ArrayList<>();
        else
            this.records = list.stream().map(PatientCarbRepresentation::new).collect(Collectors.toList());
    }
}
