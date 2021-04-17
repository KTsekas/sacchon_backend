package gr.codehub.sacchon.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FieldService<R> {
    Optional<R> get(int id);

    boolean del(int id);

    Optional<R> post(R rec);

    Optional<R> put(R rec);

    @SuppressWarnings("all")
    List<R> getList(int offset, int limit);

    double getAverage(LocalDate start, LocalDate end);

    long getMaxItems();
}
