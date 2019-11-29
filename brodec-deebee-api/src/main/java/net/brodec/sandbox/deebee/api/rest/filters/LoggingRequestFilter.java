package net.brodec.sandbox.deebee.api.rest.filters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingRequestFilter implements ContainerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRequestFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
    	LOGGER.debug("Request entity: " + getEntityBody(requestContext));		
	}

	private String getEntityBody(ContainerRequestContext requestContext) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final InputStream in = requestContext.getEntityStream();

		final StringBuilder b = new StringBuilder();
		try {
			ReaderWriter.writeTo(in, out);

			final byte[] requestEntity = out.toByteArray();
			if (requestEntity.length == 0) {
				b.append("\n");
			} else {
				b.append(new String(requestEntity)).append("\n");
			}
			requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
		} catch (final IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return b.toString();
	}
}