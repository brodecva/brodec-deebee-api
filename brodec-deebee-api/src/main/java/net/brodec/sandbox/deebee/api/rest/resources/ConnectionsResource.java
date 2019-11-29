package net.brodec.sandbox.deebee.api.rest.resources;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webcohesion.enunciate.metadata.rs.ResourceLabel;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;
import com.webcohesion.enunciate.metadata.rs.TypeHint;

import net.brodec.sandbox.deebee.api.IdGeneratingService;
import net.brodec.sandbox.deebee.dtos.ConnectionDetailDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetailService;
import net.brodec.sandbox.deebee.services.connections.DtoMapper;

/**
 * Database connections
 *
 */
@Component
@Path("/")
public class ConnectionsResource {

	//private static final Logger LOGGER = LoggerFactory.getLogger(MetadataResource.class);
	
	private final ConnectionDetailService connectionDetailService;
	
	private final IdGeneratingService idGeneratingService;
	
	private final DtoMapper dtoMapper;
	
	@Autowired
	public ConnectionsResource(final ConnectionDetailService connectionDetailService, final IdGeneratingService idGeneratingService, final DtoMapper dtoMapper) {
		checkNotNull(connectionDetailService);
		checkNotNull(idGeneratingService);
		checkNotNull(dtoMapper);

		this.connectionDetailService = connectionDetailService;
		this.idGeneratingService = idGeneratingService;
		this.dtoMapper = dtoMapper;
	}

	/**
	 * Lists the connections.
	 * 
	 * @return available database connections
	 */
	@GET
	@Path("connections")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Database connections")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Connections listed") })
	@TypeHint(Collection.class)
	public Response list() {
		final Collection<ConnectionDetail> connectionDetails = this.connectionDetailService.list();
		
		return Response.ok(connectionDetails).build();
	}
	
	/**
	 * Return the connection.
	 * 
	 * @param connectionDetailId connection ID
	 * 
	 * @return the connection detail
	 */
	@GET
	@Path("connections/{connectionDetailId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Database connection")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Desired connection returned"), @ResponseCode(code = 404, condition = "Unknown connection") })
	@TypeHint(ConnectionDetailDto.class)
	public Response get(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		return Response.ok(connectionDetail).build();
	}
	
	/**
	 * Saves the connection detail.
	 * 
	 * @param connectionDetailDto connection detail
	 * @param uriInfo URI infor
	 * @return response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("connections")
	@ResourceLabel("Connection save")
	@StatusCodes({ @ResponseCode(code = 201, condition = "Connection created"), @ResponseCode(code = 400, condition = "No or invalid connection detail provided!"),
		@ResponseCode(code = 409, condition = "Connection with the same ID already exists!") })
	public Response save(final @TypeHint(ConnectionDetailDto.class) ConnectionDetailDto connectionDetailDto, @Context UriInfo uriInfo) {
		if (connectionDetailDto == null) {
			throw new BadRequestException("No connection detail provided!");
		}
		
		final UUID connectionDetailId = getOrGenerateId(connectionDetailDto);
			
		final ConnectionDetail connectionDetail = this.dtoMapper.to(connectionDetailId, connectionDetailDto);
		
		final boolean saved = this.connectionDetailService.save(connectionDetail);
		if (saved) {
			final URI createdUri = getCreatedUri(connectionDetailId.toString(), uriInfo);		
			
			return Response.created(createdUri).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	/**
	 * Saves or updates connection.
	 * 
	 * @param connectionDetailId connection ID
	 * @param connectionDetailDto connection detail
	 * @param uriInfo URI info
	 * @return response
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("connections/{connectionDetailId}")
	@ResourceLabel("Connection update or save")
	@StatusCodes({ @ResponseCode(code = 201, condition = "Connection created"), @ResponseCode(code = 400, condition = "No or invalid connection detail provided!"),
		@ResponseCode(code = 204, condition = "Connectio updated") })
	public Response saveOrUpdate(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId, final @TypeHint(ConnectionDetailDto.class) ConnectionDetailDto connectionDetailDto, @Context UriInfo uriInfo) {
		if (connectionDetailDto == null) {
			throw new BadRequestException("No connection detail provided!");
		}
		
		if (!connectionDetailId.equals(connectionDetailDto.getId())) {
			throw new BadRequestException("The connection ID does not conform to the one in path!");
		}
		
		final ConnectionDetail connectionDetail = this.dtoMapper.to(connectionDetailDto);
		
		final boolean created = this.connectionDetailService.saveOrUpdate(connectionDetail);
		if (created) {
			final URI createdUri = getCreatedUri(connectionDetailId.toString(), uriInfo);		
			
			return Response.created(createdUri).build();
		} else {
			return Response.noContent().build();
		}
	}

	private URI getCreatedUri(final String relativePath, UriInfo uriInfo) {
		return uriInfo.getAbsolutePathBuilder().path(relativePath).build();
	}
	
	/**
	 * Deletes the connection.
	 * 
	 * @param connectionDetailId connection ID
	 * @return response
	 */
	@DELETE
	@Path("connections/{connectionDetailId}")
	@ResourceLabel("Connection deletion")
	@StatusCodes({ @ResponseCode(code = 404, condition = "Nonexistent connection"),
		@ResponseCode(code = 204, condition = "Connectio deleted") })
	public Response delete(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId) {
		final boolean deleted = this.connectionDetailService.delete(connectionDetailId);
		if (!deleted) {
			throw new NotFoundException("Nonexistent connection!");
		}
		
		return Response.noContent().build();
	}
	
	private UUID getOrGenerateId(final ConnectionDetailDto connectionDetailDto) {
		if (connectionDetailDto.getId() == null) {
			return this.idGeneratingService.generate();
		} else {
			return connectionDetailDto.getId();
		}
	}
}
