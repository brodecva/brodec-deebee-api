package net.brodec.sandbox.deebee.api.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingResponseFilter implements ContainerResponseFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingResponseFilter.class);

  @Override
  public void filter(final ContainerRequestContext requestContext,
      final ContainerResponseContext responseContext) throws IOException {
    final String method = requestContext.getMethod();

    LOGGER.debug("Requesting " + method + " for path " + requestContext.getUriInfo().getPath());
    
    final Object entity = responseContext.getEntity();
    if (entity != null) {
    	LOGGER.debug("Reply "
          + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity));
    }
  }

}
