package gr.codehub.sacchon.model;

public class UserRole {
    public static final String PATIENT="patient";
    public static final String DOCTOR="doctor";
    public static final String REPORTER="reporter";

    private UserRole(){}

    public static boolean isValidSignUpRole(String name){
        return name.equals(PATIENT) || name.equals(DOCTOR);
    }
}
