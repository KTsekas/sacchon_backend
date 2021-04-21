package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.representations.reporter.ConsultationRepresentation;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.ConsultationService;
import gr.codehub.sacchon.services.DoctorService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorConsultationsResource extends DateRangeResource {

    @Get("json")
    public List<ConsultationRepresentation> getConsults() {

        int id = ResourceHelper.parseIntQueryOrFail("id", this);
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", 0, this);
        DoctorService srv = new DoctorService();
        Optional<Doctor> d = srv.getDoctor(id);
        if (d.isEmpty()) {
            setStatus(Status.CLIENT_ERROR_NOT_FOUND, "No patient found with that id");
            srv.close();
            return null;
        }
        ConsultationService consultSrv = new ConsultationService();
        srv.close();
        setService(consultSrv);
        return consultSrv.getByDate(d.get(),getStart(), getEnd(), offset, limit).stream().map(ConsultationRepresentation::new).collect(Collectors.toList());
    }
}
