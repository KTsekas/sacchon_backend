package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbForm {
    public static final int MISSING_ID_VALUE = -1;
    private int id = MISSING_ID_VALUE;
    private double carbIntake;
    private String date;

    public LocalDate getLocalDate() {
        return DateHelper.getDate(date);
    }
}
