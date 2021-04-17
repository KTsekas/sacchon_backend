package gr.codehub.sacchon.representations;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationListRepresentation<T> {
    int offset;
    long maxItems;
    List<T> items;

    public PaginationListRepresentation(int offset, long maxItems,List<T> items ){
        if ( items == null )
            items = new ArrayList<>();
        this.items=items;
        this.offset=offset;
        this.maxItems = maxItems;
    }
}
