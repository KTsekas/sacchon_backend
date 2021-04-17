package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.forms.FieldForm;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class FieldResource<T,F extends FieldForm<T>, R> extends AuthResource {

    abstract FieldService<T> getService();

    abstract R getRepresentation(T item);


    @Get("json")
    public PaginationListRepresentation<R> getList() {
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        FieldService<T> srv= getService();
        List<R> items = getService().getList(offset,limit).stream().map(this::getRepresentation).collect(Collectors.toList());
        long maxItems = srv.getMaxItems(); // its supposed to be a SELECT COUNT
        return new PaginationListRepresentation<>(offset, maxItems, items);
    }

    @Post("json")
    public void insert(F frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        frm.process();
        if( !frm.isPostValid())
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "invalid format in body");
        if (getService().post(frm.create()).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "unable to save record in database");
    }

    @Put("json")
    public void update(F frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        frm.process();
        if (!frm.isPutValid())
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "invalid format in body");
        FieldService<T> srv = getService();
        Optional<T> rec = srv.get(frm.getId());
        if (rec.isEmpty())
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "no record found with this id");
        frm.update(rec.get());
        if (srv.put(rec.get()).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "unable to update record in database");
    }

    @Delete("json")
    public void deleteRecord() {
        int id = ResourceHelper.parseIntOrDef("id", -1, this);
        if (id == -1)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "invalid id attribute");
        if (!getService().del(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "unable to delete record");
    }
}
