package tech.fabricate.mpdemo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/issvisible")
public class ISSVisibleResource {

  @Inject
  private Config config;

  @GET
  @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
  public Response doGetText() {
    return Response.ok().build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response doGetJson() {
    return Response.ok().build();
  }
}
