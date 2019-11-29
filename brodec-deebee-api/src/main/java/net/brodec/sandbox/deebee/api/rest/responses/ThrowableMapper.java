package net.brodec.sandbox.deebee.api.rest.responses;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.JsonMappingException;

public class ThrowableMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(final Throwable throwable) {
		final StatusType statusType = getHttpStatus(throwable);
		final String text = throwable.getMessage();

		final ResponseBuilder builder = Response.status(statusType);

		if (text == null) {
			return builder.build();
		} else {
			return builder.type(MediaType.TEXT_PLAIN_TYPE).entity(text).build();
		}
	}
	
	private StatusType getHttpStatus(final Throwable throwable) {
		if (throwable instanceof WebApplicationException) {
			return ((WebApplicationException) throwable).getResponse().getStatusInfo();
		} else if (throwable instanceof JsonMappingException) {
			return Status.BAD_REQUEST;
		} else if (throwable instanceof IllegalArgumentException) {
			return Status.BAD_REQUEST;
		} else {
			return Response.Status.INTERNAL_SERVER_ERROR;
		}
	}
}
