package gr.codehub.sacchon.util;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

    public static final LocalDate MAX = LocalDate.of(9999,12,28);
    public static final LocalDate MIN = LocalDate.of(-9999,1,1);
    // returns local date from ISO-DATE string or null
    public static LocalDate getDate(String date){
        try{
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        }catch(DateTimeParseException ex){
            return null;
        }
    }
    public static LocalDate getIfDateOrDefault(String date,LocalDate def){
        if ( date == null )
            return def;
        return getDate(date);
    }
    public static LocalDateTime getLocalDateTimeFromUTC(long epochMilli){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }
}
