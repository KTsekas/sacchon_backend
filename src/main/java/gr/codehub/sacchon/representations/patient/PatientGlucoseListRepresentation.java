package gr.codehub.sacchon.representations.patient;


import gr.codehub.sacchon.model.GlucoseRecord;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PatientGlucoseListRepresentation {
    List<PatientGlucoseRepresentation> records;
    int count;

    public PatientGlucoseListRepresentation(List<GlucoseRecord> list,int count){
        this.records = list.stream().map(PatientGlucoseRepresentation::new).collect(Collectors.toList());
        this.count =count;
    }
}
