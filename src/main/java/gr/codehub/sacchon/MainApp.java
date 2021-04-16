package gr.codehub.sacchon;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.UserRole;
import gr.codehub.sacchon.routers.AuthRouter;
import gr.codehub.sacchon.routers.DoctorRouter;
import gr.codehub.sacchon.routers.PatientRouter;
import gr.codehub.sacchon.security.RoleVerifier;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class MainApp extends Application {
    public static final Logger LOGGER = Engine.getLogger(MainApp.class);
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        Component c = new Component();

        c.getServers().add(Protocol.HTTP,"localhost", 9000);
        c.getDefaultHost().attach("/api", new MainApp());
        c.start();

        LOGGER.info("Sample Web API started");
        LOGGER.info(String.format("URL: http://%s:%d",
                c.getServers().get(0).getAddress(),
                c.getServers().get(0).getPort()));
    }

    private ChallengeAuthenticator getRoleGuard(Router router,String role){
        ChallengeAuthenticator guard = new ChallengeAuthenticator(this.getContext(), ChallengeScheme.HTTP_BASIC,"sacchon");
        guard.setVerifier( new RoleVerifier(this,role));
        guard.setNext(router);
        return guard;
    }

    @Override
    public Restlet createInboundRoot() {
        AuthRouter auth = new AuthRouter();
        PatientRouter patient = new PatientRouter();
        DoctorRouter doctor = new DoctorRouter();
        Router router = new Router();

        auth.setupEndPoints();
        patient.setupEndPoints();

        router.attach("/auth",auth);
        router.attach("/patient",getRoleGuard(patient,UserRole.PATIENT));
        router.attach("/doctor",getRoleGuard(doctor,UserRole.DOCTOR));
//        CustomRouter customRouter = new CustomRouter(this);
//        Shield shield = new Shield(this);
//
//        Router publicRouter = customRouter.publicResources();
//        ChallengeAuthenticator apiGuard = shield.createApiGuard();
//        // Create the api router, protected by a guard
//
//        Router apiRouter = customRouter.protectedResources();
//        apiGuard.setNext(apiRouter);
//
//        publicRouter.attachDefault(apiGuard);
//
//        // return publicRouter;
//
//        CorsFilter corsFilter = new CorsFilter(this);
//        return corsFilter.createCorsFilter(publicRouter);
        return router;
    }

}
