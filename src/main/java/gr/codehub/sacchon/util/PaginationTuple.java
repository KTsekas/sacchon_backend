package gr.codehub.sacchon.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationTuple<T> {
     List<T> items;
     int offset;
}
