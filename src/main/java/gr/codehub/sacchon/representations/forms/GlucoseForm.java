package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

@Data
public class GlucoseForm {
    public static final int MISSING_ID_VALUE=-1;
    private int id=MISSING_ID_VALUE;
    private double glucoseLevel;
    private long dateTime;



    public GlucoseRecord create(){
        return update(new GlucoseRecord());
    }
    public GlucoseRecord update(GlucoseRecord rec){
        rec.setGlucoseLevel(glucoseLevel);
        rec.setDateTime(DateHelper.getLocalDateTimeFromUTC(dateTime));
        return rec;
    }
}
