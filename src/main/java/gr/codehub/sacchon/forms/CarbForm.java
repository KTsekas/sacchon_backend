package gr.codehub.sacchon.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.util.LocalDateDeserializer;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbForm implements FieldForm<CarbRecord> {
    public static final int MISSING_ID_VALUE = -1;
    private int id = MISSING_ID_VALUE;
    private double carbIntake;

    @JsonSerialize( using = LocalDateSerializer.class)
    @JsonDeserialize( using = LocalDateDeserializer.class)
    private LocalDate date;

    public CarbRecord create() {
        return update(new CarbRecord());
    }

    public CarbRecord update(CarbRecord rec) {
        rec.setCarbIntake(carbIntake);
        rec.setDate(date);
        return rec;
    }

    public boolean isPutValid() {
        return this.id != MISSING_ID_VALUE && isPostValid();
    }

    public boolean isPostValid() {
        return true;
    }
}
