package gr.codehub.sacchon;

import gr.codehub.sacchon.routers.AppRouter;
import gr.codehub.sacchon.util.JpaUtil;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

class WebApp extends Application{

    @Override
    public Restlet createInboundRoot() {
        return new AppRouter(this.getContext()).createRouter();
    }
}

public class MainApp {

    public static final Logger LOGGER = Engine.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        Component c = new Component();

        c.getServers().add(Protocol.HTTP,"localhost", 9000);
        c.getDefaultHost().attach("/api", new WebApp());
        c.start();
        LOGGER.info("Sample Web API started");
        LOGGER.info(String.format("URL: http://%s:%d",
                c.getServers().get(0).getAddress(),
                c.getServers().get(0).getPort()));
    }

}
