package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.SingleValueRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.GlucoseService;
import net.bytebuddy.asm.Advice;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class AvgResource extends AuthResource {

    @Get("json")
    public SingleValueRepresentation<Double> doAvg() {
        String type = getAttribute("type");
        if (type == null) {
            setStatus(Status.SERVER_ERROR_INTERNAL, "False routing from server");
            return null;
        }
        Optional<LocalDate> start = getDateQueryValue("start",LocalDate.of(-9999,1,1));
        if (start.isEmpty()) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid start date");
            return null;
        }
        Optional<LocalDate> end = getDateQueryValue("end",LocalDate.of(9999,1,1));
        if (end.isEmpty()) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid end date");
            return null;
        }
        if (type.equals("carb"))
            return getCarbAverage(start.get(), end.get());
        else if (type.equals("glucose"))
            return getGlucoseAverage(start.get(), end.get());
        else
            return new SingleValueRepresentation<>(0.0);
    }
    private SingleValueRepresentation<Double> getCarbAverage(LocalDate start, LocalDate end) {
        CarbService srv = new CarbService((Patient) getUser());
        setService(srv);
        return new SingleValueRepresentation<>(srv.getAverage(start, end));
    }

    private SingleValueRepresentation<Double> getGlucoseAverage(LocalDate start, LocalDate end) {
        GlucoseService srv = new GlucoseService((Patient) getUser());
        setService(srv);
        return new SingleValueRepresentation<>(srv.getAverage(start, end));
    }


    private Optional<LocalDate> getDateQueryValue(String name,LocalDate def){
        String q = this.getQueryValue(name);
        if ( q == null )
            return Optional.of(def);
        try{
            return Optional.of(LocalDate.parse(q, DateTimeFormatter.ISO_LOCAL_DATE));
        }catch(DateTimeParseException ex){
            return Optional.empty();
        }
    }

}
