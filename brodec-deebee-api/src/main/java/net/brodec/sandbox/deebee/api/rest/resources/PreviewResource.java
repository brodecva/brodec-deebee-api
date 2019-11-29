package net.brodec.sandbox.deebee.api.rest.resources;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webcohesion.enunciate.metadata.rs.ResourceLabel;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;
import com.webcohesion.enunciate.metadata.rs.TypeHint;

import net.brodec.sandbox.deebee.dtos.ResultDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetailService;
import net.brodec.sandbox.deebee.services.preview.PreviewService;

/**
 * Table preview
 *
 */
@Component
@Path("/")
public class PreviewResource {

	private final ConnectionDetailService connectionDetailService;

	private final PreviewService previewService;

	@Autowired
	public PreviewResource(final ConnectionDetailService connectionDetailService, final PreviewService previewService) {
		checkNotNull(connectionDetailService);
		checkNotNull(previewService);

		this.connectionDetailService = connectionDetailService;
		this.previewService = previewService;
	}

	/**
	 * Provides the table content preview.
	 * 
	 * @param connectionDetailId connection ID
	 * @param table table
	 * @param limit limit
	 * @param offset offset
	 * @return preview result in the form of fields and array of row arrays
	 */
	@GET
	@Path("connections/{connectionDetailId}/tables/{table}/preview")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table preview")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Preview obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid limit or offset provided!") })
	@TypeHint(ResultDto.class)
	public Response preview(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("table") String table, final @QueryParam("limit") Integer limit,
			final @QueryParam("offset") Integer offset) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final ResultDto resultDto = this.previewService.preview(connectionDetail, null, table, offset, limit);

		return Response.ok(resultDto).build();
	}

	/**
	 * Provides the table content preview.
	 * 
	 * @param connectionDetailId connection ID
	 * @param schema schema
	 * @param table table
	 * @param limit limit
	 * @param offset offset
	 * @return preview result in the form of fields and array of row arrays
	 */
	@GET
	@Path("connections/{connectionDetailId}/schemas/{schema}/tables/{table}/preview")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table preview")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Preview obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid limit or offset provided!") })
	@TypeHint(ResultDto.class)
	public Response preview(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("schema") String schema, final @PathParam("table") String table,
			final @QueryParam("limit") Integer limit, final @QueryParam("offset") Integer offset) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final ResultDto resultDto = this.previewService.preview(connectionDetail, schema, table, offset, limit);

		return Response.ok(resultDto).build();
	}
}
