package Klasses;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class WebService {
    @POST
    @Path("/callJavaMethod")
    public Response callJavaMethod() {
        JavaGetJavaScript.getSignal();
        return Response.ok().build();
    }

    private boolean checkLogin(String username, String email, String password) {
        return LoginController.compareLoginInformation(username, email, password);
    }

    private void sendSignal(String signal) {
        // Hier wird das Signal an JavaScript gesendet
        // Code zum Senden des Signals an JavaScript
    }
}
