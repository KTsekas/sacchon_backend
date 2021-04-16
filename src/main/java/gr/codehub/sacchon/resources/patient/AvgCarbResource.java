package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.util.JpaUtil;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.services.PatientCarbRepository;
import gr.codehub.sacchon.representations.AvgRepresentation;
import gr.codehub.sacchon.util.DateHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;

public class AvgCarbResource extends AuthResource {


    @Get("json")
    public AvgRepresentation doAvg(){
        String start = this.getQueryValue("start");
        String end = this.getQueryValue("end");
        LocalDate start_date= DateHelper.getIfDateOrDefault(start,DateHelper.MIN);
        LocalDate end_date = DateHelper.getIfDateOrDefault(end,DateHelper.MAX);
        System.out.println(start);
        if( start_date == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid start date");
        if ( end_date == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid end date");
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        return new AvgRepresentation(repo.getAverage(start_date, end_date));
    }


}
