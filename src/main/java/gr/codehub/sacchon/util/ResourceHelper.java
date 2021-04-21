package gr.codehub.sacchon.util;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ResourceHelper {
    public static int parseIntOrDef(String name,int def,ServerResource res){
        try{
            return Integer.parseInt(res.getQueryValue(name));
        }catch(NumberFormatException ex){
            return def;
        }
    }
    public static int parseIntQueryOrFail(String name,ServerResource res){
        try{
            return Integer.parseInt(res.getQueryValue(name));
        }
        catch(NumberFormatException ex){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,name+ " not provided or invalid format");
        }
    }


}