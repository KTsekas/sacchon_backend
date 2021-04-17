package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GlucoseForm implements FieldForm<GlucoseRecord> {
    public static final int MISSING_ID_VALUE=-1;
    private int id=MISSING_ID_VALUE;
    private double glucoseLevel;
    private long dateTime;
    private LocalDateTime dateTimeObject;

    public void process(){
        this.dateTimeObject = DateHelper.getLocalDateTimeFromUTC(dateTime);
    }

    public GlucoseRecord create(){
        return update(new GlucoseRecord());
    }
    public GlucoseRecord update(GlucoseRecord rec){
        rec.setGlucoseLevel(glucoseLevel);
        rec.setDateTime(dateTimeObject);
        return rec;
    }
    public boolean isPutValid() {
        return this.id != MISSING_ID_VALUE && isPostValid();
    }
    public boolean isPostValid(){
        return this.dateTimeObject != null;
    }
}
