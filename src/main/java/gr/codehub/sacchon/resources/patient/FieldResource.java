package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.PatientField;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.forms.FieldForm;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class FieldResource<T extends PatientField,F extends FieldForm<T>, R> extends AuthResource {

    abstract FieldService<T> getService();

    abstract R getRepresentation(T item);

    @Get("json")
    public PaginationListRepresentation<R> getList() {
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        FieldService<T> srv= getService();
        PaginationTuple<T> items = srv.getList(offset,limit);
        return new PaginationListRepresentation<>(
                items.getItems().stream().map(this::getRepresentation).collect(Collectors.toList()),
                offset);
    }

    @Post("json")
    public void insert(F frm) {
        if (frm == null){
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
            return;
        }
        if( !frm.isPostValid()){
            setStatus(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "Invalid format in body");
            return;
        }
        System.out.println(frm);
        if (getService().post(frm.create()).isEmpty())
            setStatus(Status.SERVER_ERROR_INTERNAL, "Unable to save record in database");
    }

    @Put("json")
    public void update(F frm) {
        if (frm == null) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
            return;
        }
        if (!frm.isPutValid()){
            setStatus(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "Invalid format in body");
            return;
        }
        FieldService<T> srv = getService();
        Optional<T> rec = srv.get(frm.getId());
        if (rec.isEmpty()){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND, "No record found with this id");
            return;
        }
        frm.update(rec.get());
        if (srv.put(rec.get()).isEmpty())
            setStatus(Status.SERVER_ERROR_INTERNAL, "Unable to update record in database");
    }

    @Delete("json")
    public void deleteRecord() {
        int id = ResourceHelper.parseIntOrDef("id", -1, this);
        if (id == -1) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid id attribute");
            return;
        }
        if (!getService().del(id))
            setStatus(Status.CLIENT_ERROR_NOT_FOUND, "Unable to delete record");
    }
}
