package gr.codehub.sacchon.representations;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationListRepresentation<T> {
    int offset;
    List<T> items;

    public PaginationListRepresentation(List<T> items,int offset ){
        if ( items == null )
            items = new ArrayList<>();
        this.items=items;
        System.out.println(items.size());
        this.offset=offset;
    }
}
