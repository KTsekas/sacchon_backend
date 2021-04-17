package gr.codehub.sacchon.forms;

public interface FieldForm<T> {

    void process();


    int getId();

    T create();

    T update(T rec);

    boolean isPutValid();

    boolean isPostValid();
}
