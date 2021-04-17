package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbForm implements FieldForm<CarbRecord> {
    public static final int MISSING_ID_VALUE = -1;
    private int id = MISSING_ID_VALUE;
    private double carbIntake;
    private String date;
    private LocalDate dateObject;

    @Override
    public void process(){
        this.dateObject = DateHelper.getDate(date);
    }
    @Override
    public CarbRecord create(){
        return update(new CarbRecord());
    }
    @Override
    public CarbRecord update(CarbRecord rec){
        rec.setCarbIntake(carbIntake);
        rec.setDateCreated(DateHelper.getDate(date));
        return rec;
    }
    @Override
    public boolean isPutValid() {
        return this.id != MISSING_ID_VALUE && isPostValid();
    }
    @Override
    public boolean isPostValid(){
        return this.dateObject != null;
    }
}
