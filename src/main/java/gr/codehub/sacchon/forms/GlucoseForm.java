package gr.codehub.sacchon.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.LocalDateDeserializer;
import gr.codehub.sacchon.util.LocalDateSerializer;
import gr.codehub.sacchon.util.LocalTimeDeserializer;
import gr.codehub.sacchon.util.LocalTimeSerializer;
import lombok.Data;

import java.time.LocalTime;
import java.time.LocalDate;

@Data
public class GlucoseForm implements FieldForm<GlucoseRecord> {
    public static final int MISSING_ID_VALUE = -1;
    private int id = MISSING_ID_VALUE;
    private double glucoseLevel;

    @JsonSerialize( using = LocalDateSerializer.class)
    @JsonDeserialize( using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonSerialize( using = LocalTimeSerializer.class)
    @JsonDeserialize( using = LocalTimeDeserializer.class)
    private LocalTime time;


    public GlucoseRecord create() {
        return update(new GlucoseRecord());
    }

    public GlucoseRecord update(GlucoseRecord rec) {
        rec.setGlucoseLevel(glucoseLevel);
        rec.setDate(LocalDate.now());
        rec.setTime(null);
        return rec;
    }

    public boolean isPutValid() {
        return this.id != MISSING_ID_VALUE && isPostValid();
    }
    public boolean isPostValid() {
        return true;
    }
}