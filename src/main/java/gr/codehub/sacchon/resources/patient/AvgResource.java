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

public class AvgResource extends AuthResource {

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

    @Get("json")
    public SingleValueRepresentation<Double> doAvg() {
        String type = getAttribute("type");
        if (type == null) {
            setStatus(Status.SERVER_ERROR_INTERNAL, "False routing from server");
            return null;
        }
        String start = this.getQueryValue("start");
        String end = this.getQueryValue("end");
//        LocalDate start_date = DateHelper.getIfDateOrDefault(start, DateHelper.MIN);
//        LocalDate end_date = DateHelper.getIfDateOrDefault(end, DateHelper.MAX);
        LocalDate start_date=null;
        LocalDate end_date=null;
        if (start_date == null) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid start date");
            return null;
        }
        if (end_date == null){
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid end date");
            return null;
        }
        if (type.equals("carb"))
            return getCarbAverage(start_date, end_date);
        else if (type.equals("glucose"))
            return getGlucoseAverage(start_date, end_date);
        else
            return new SingleValueRepresentation<>(0.0);
    }


}
