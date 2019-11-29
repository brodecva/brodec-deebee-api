package net.brodec.sandbox.deebee.api.rest.resources;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webcohesion.enunciate.metadata.rs.ResourceLabel;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;
import com.webcohesion.enunciate.metadata.rs.TypeHint;

import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.SchemaDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetailService;
import net.brodec.sandbox.deebee.services.metadata.MetadataService;

/**
 * Database metadata
 *
 */
@Component
@Path("/")
public class MetadataResource {

	//private static final Logger LOGGER = LoggerFactory.getLogger(MetadataResource.class);
	
	private final ConnectionDetailService connectionDetailService;
	
	private final MetadataService metadataService;

	@Autowired
	public MetadataResource(final ConnectionDetailService connectionDetailService, final MetadataService metadataService) {
		checkNotNull(connectionDetailService);
		checkNotNull(metadataService);

		this.connectionDetailService = connectionDetailService;
		this.metadataService = metadataService;
	}

	/**
	 * Lists the DB schemas, if supported.
	 * 
	 * @param connectionDetailId connection ID
	 * @return list of schemas
	 */
	@GET
	@Path("connections/{connectionDetailId}/metadata/schemas")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Database schemas")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Schemas listed"),
		@ResponseCode(code = 404, condition = "Unknown connection") })
	@TypeHint(List.class)
	public Response schemas(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final List<SchemaDto> schemas = this.metadataService.schemas(connectionDetail);
		
		return Response.ok(schemas).build();
	}
	
	/**
	 * Lists DB tables, without concerning with the schema support or organization.
	 * 
	 * @param connectionDetailId connection ID
	 * @return list of tables
	 */
	@GET
	@Path("connections/{connectionDetailId}/metadata/tables")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Database tables")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Tables listed"),
		@ResponseCode(code = 404, condition = "Unknown connection") })
	@TypeHint(List.class)
	public Response tables(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final List<TableDto> tables = this.metadataService.tables(connectionDetail, null);
		
		return Response.ok(tables).build();
	}
	
	/**
	 * Lists tables in the schema.
	 * 
	 * @param connectionDetailId connection ID
	 * @param schema schema
	 * @return list of tables in the schema
	 */
	@GET
	@Path("connections/{connectionDetailId}/metadata/schemas/{schema}/tables")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Database tables")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Tables listed"),
		@ResponseCode(code = 404, condition = "Unknown connection") })
	@TypeHint(List.class)
	public Response tables(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId, final @PathParam("schema") String schema) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final List<TableDto> tables = this.metadataService.tables(connectionDetail, schema);
		
		return Response.ok(tables).build();
	}
	
	/**
	 * Lists the table columns.
	 * 
	 * @param connectionDetailId connection ID
	 * @param table table
	 * @return list of columns in order
	 */
	@GET
	@Path("connections/{connectionDetailId}/metadata/tables/{table}/columns")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table columns")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Columns listed"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table provided!") })
	@TypeHint(List.class)
	public Response columns(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId, final @PathParam("table") String table) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final List<FieldDto> columns = this.metadataService.columns(connectionDetail, null, table);
		
		return Response.ok(columns).build();
	}
	
	/**
	 * Lists the table columns.
	 * 
	 * @param connectionDetailId connection ID
	 * @param schema schema
	 * @param table table
	 * @return list of columns in order
	 */
	@GET
	@Path("connections/{connectionDetailId}/metadata/schemas/{schema}/tables/{table}/columns")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table columns")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Columns listed"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table or schema provided!") })
	@TypeHint(List.class)
	public Response columns(final @PathParam("connectionDetailId") UUID connectionDetailId, final @PathParam("schema") String schema, final @PathParam("table") String table) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final List<FieldDto> columns = this.metadataService.columns(connectionDetail, schema, table);
		
		return Response.ok(columns).build();
	}
}
