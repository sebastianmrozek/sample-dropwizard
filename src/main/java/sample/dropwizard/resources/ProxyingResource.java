package sample.dropwizard.resources;

import org.glassfish.jersey.client.ClientConfig;
import sample.dropwizard.ResponseTimingFilter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Path("server")
public class ProxyingResource {

    private final WebTarget webTarget;

    public ProxyingResource() {
        this.webTarget = createClient();
    }

    @GET
    @Path("1")
    public String proxyCallToOtherServer() {
        return webTarget.path("hardwork").request().get(String.class);
    }

    @GET
    @Path("2")
    public String proxyCallToMoreServer() {
        return webTarget.path("morework").request().get(String.class);
    }

    private WebTarget createClient() {
        ClientConfig clientConfig = new ClientConfig().register(ResponseTimingFilter.class);
        Client client = ClientBuilder.newClient(clientConfig);
        return client.target("http://localhost:8080/other");
    }
}
