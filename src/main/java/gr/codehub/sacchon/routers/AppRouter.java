package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.model.UserRole;
import gr.codehub.sacchon.security.RoleVerifier;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Method;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.routing.Filter;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.util.Series;

import java.util.Collections;
import java.util.HashSet;

public class AppRouter {

    private final Context ctx;

    public AppRouter(Context ctx) {
        this.ctx = ctx;
    }

    @SuppressWarnings("all")
    private Filter createCorsFilter(Restlet next) {
        return new Filter(this.ctx, next) {

            @Override
            protected int beforeHandle(Request request, Response response) {
                // Initialize response headers

                Series<Header> responseHeaders = (Series<Header>) response
                        .getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
                if (responseHeaders == null) {
                    responseHeaders = new Series<Header>(Header.class);
                }

                // Request headers

                Series<Header> requestHeaders = (Series<Header>) request.getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
                String requestOrigin = requestHeaders.getFirstValue("Origin", false, "*");
                String rh = requestHeaders.getFirstValue("Access-Control-Request-Headers", false, "*");

                response.setAccessControlAllowCredentials(true);
                response.setAccessControlAllowOrigin(requestOrigin);
                response.setAccessControlAllowHeaders(Collections.singleton("*"));

                HashSet<Method> methodHashSet = new HashSet<>();
                methodHashSet.add(Method.GET);
                methodHashSet.add(Method.POST);
                methodHashSet.add(Method.PUT);
                methodHashSet.add(Method.DELETE);

                response.setAccessControlAllowMethods(methodHashSet);

                // Set response headers

                response.getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS, responseHeaders);

                // Handle HTTP methods

                if (Method.OPTIONS.equals(request.getMethod())) {
                    return Filter.STOP;
                }
                return super.beforeHandle(request, response);
            }
        };
    }

    private ChallengeAuthenticator getRoleGuard(Router router, String role) {
        ChallengeAuthenticator guard = new ChallengeAuthenticator(this.ctx, ChallengeScheme.HTTP_BASIC, "sacchon");
        guard.setVerifier(new RoleVerifier(this.ctx, role));
        guard.setNext(router);
        return guard;
    }

    public Restlet createRouter() {

        AuthRouter auth = new AuthRouter();
        PatientRouter patient = new PatientRouter();
        DoctorRouter doctor = new DoctorRouter();
        ReporterRouter reporter = new ReporterRouter();

        auth.setupEndPoints();
        patient.setupEndPoints();
        doctor.setupEndPoints();
        reporter.setupEndPoints();

        Router router = new Router();
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);


        router.attach("/auth", auth);
        router.attach("/patient", getRoleGuard(patient, UserRole.PATIENT));
        router.attach("/doctor", getRoleGuard(doctor, UserRole.DOCTOR));
        router.attach("/reporter", getRoleGuard(reporter, UserRole.REPORTER));

        return createCorsFilter(router);
    }
}
