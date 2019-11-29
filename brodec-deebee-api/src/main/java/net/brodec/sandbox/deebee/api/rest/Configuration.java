/**
 *
 */
package net.brodec.sandbox.deebee.api.rest;

import javax.xml.bind.JAXBException;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import net.brodec.sandbox.deebee.api.rest.filters.CorsResponseFilter;
import net.brodec.sandbox.deebee.api.rest.filters.LoggingRequestFilter;
import net.brodec.sandbox.deebee.api.rest.filters.LoggingResponseFilter;
import net.brodec.sandbox.deebee.api.rest.resources.ConnectionsResource;
import net.brodec.sandbox.deebee.api.rest.resources.MetadataResource;
import net.brodec.sandbox.deebee.api.rest.resources.PreviewResource;
import net.brodec.sandbox.deebee.api.rest.resources.StatisticsResource;
import net.brodec.sandbox.deebee.api.rest.resources.WelcomeResource;
import net.brodec.sandbox.deebee.api.rest.responses.ThrowableMapper;

@Component
public final class Configuration extends ResourceConfig {

  public Configuration() throws JAXBException {
    // Resources registration
    register(WelcomeResource.class);
    register(MetadataResource.class);
    register(PreviewResource.class);
    register(StatisticsResource.class);
    register(ConnectionsResource.class);

    // Filters registration
    register(RequestContextFilter.class);
    register(LoggingRequestFilter.class);
    register(LoggingResponseFilter.class);
    register(CorsResponseFilter.class);

    // Exception mappers registration
    register(ThrowableMapper.class);

    // Prevent the container to interfere with the error entities.
    property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
    property(ServletProperties.FILTER_FORWARD_ON_404, true);
  }
}
