package gr.codehub.sacchon.resources;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateRangeResource extends AuthResource {
    LocalDate start;
    LocalDate end;

    protected LocalDate getStart() { return start;}
    protected LocalDate getEnd() { return end;}


    private LocalDate getDateQueryValue(String name, LocalDate def) {
        String q = this.getQueryValue(name);
        if (q == null)
            return def;
        try {
            return LocalDate.parse(q, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        start = getDateQueryValue("start", LocalDate.of(-9999, 1, 1));
        if (start == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid start date");
        end = getDateQueryValue("end", LocalDate.of(9999, 1, 1));
        if (end == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid end date");
    }
}
