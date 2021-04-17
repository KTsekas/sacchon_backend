package gr.codehub.sacchon.util;

import org.restlet.resource.ServerResource;

public class ResourceHelper {
    public static int parseIntOrDef(String name,int def,ServerResource res){
        try{
            return Integer.parseInt(res.getQueryValue(name));
        }catch(NumberFormatException ex){
            return def;
        }
    }
}