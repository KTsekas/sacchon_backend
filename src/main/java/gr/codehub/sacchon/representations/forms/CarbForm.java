package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class CarbForm {
    public static final int MISSING_ID_VALUE = -1;
    private int id = MISSING_ID_VALUE;
    private double carbIntake;
    private String date;

    public CarbRecord create(){
        return update(new CarbRecord());
    }
    public CarbRecord update(CarbRecord rec){
        rec.setCarbIntake(carbIntake);
        rec.setDateCreated(DateHelper.getDate(date));
        return rec;
    }
    public Optional<LocalDate> getLocalDate(){
        return Optional.ofNullable(DateHelper.getDate(date));
    }
}
