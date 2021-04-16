package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.util.DateHelper;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvgForm {
    String start;
    String end;

    public LocalDate getStartDate(){
        return DateHelper.getDate(start);
    }
    public LocalDate getEndDate(){
        return DateHelper.getDate(end);
    }
}
