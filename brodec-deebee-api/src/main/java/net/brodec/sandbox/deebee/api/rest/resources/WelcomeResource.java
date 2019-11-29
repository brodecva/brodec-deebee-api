package net.brodec.sandbox.deebee.api.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Welcome page.
 *
 * @author Václav Brodec
 */
@Path("/")
public final class WelcomeResource {

  private static final String WELCOME_PAGE_CONTENT =
      "<html>" + "<title>Brodec Deebee API</title>" + "<body>"
          + "<h1>Brodec Deebee API is working!</h1>"
          + "</body>" + "</html>";

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String welcome() {
    return WELCOME_PAGE_CONTENT;
  }
}
