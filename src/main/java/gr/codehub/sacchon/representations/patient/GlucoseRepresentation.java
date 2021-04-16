package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

@Data
public class GlucoseRepresentation {

    private int id;
    private double glucoseLevel;
    private long dateTime;

    public GlucoseRepresentation(GlucoseRecord rec){
        if( rec == null)
            return;
        this.id = rec.getId();
        this.glucoseLevel=rec.getGlucoseLevel();
        this.dateTime= DateHelper.getUTCFromLocalDateTime(rec.getDateTime());
    }
}
