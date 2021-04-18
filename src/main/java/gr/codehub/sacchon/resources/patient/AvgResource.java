package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.SingleValueRepresentation;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.GlucoseService;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.time.LocalDate;

public class AvgResource extends DateRangeResource {

    @Get("json")
    public SingleValueRepresentation<Double> doAvg() {
        String type = getAttribute("type");
        if (type == null) {
            setStatus(Status.SERVER_ERROR_INTERNAL, "False routing from server");
            return null;
        }
        if (type.equals("carb"))
            return getCarbAverage(getStart(),getEnd());
        else if (type.equals("glucose"))
            return getGlucoseAverage(getStart(), getEnd());
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



}
