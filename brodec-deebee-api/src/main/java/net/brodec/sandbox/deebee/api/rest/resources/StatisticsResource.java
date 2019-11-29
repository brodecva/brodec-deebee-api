package net.brodec.sandbox.deebee.api.rest.resources;

import static com.google.common.base.Preconditions.checkNotNull;

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
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetailService;
import net.brodec.sandbox.deebee.services.statistics.StatisticsService;

/**
 * Table statistics
 *
 */
@Component
@Path("/")
public class StatisticsResource {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MetadataResource.class);

	private final ConnectionDetailService connectionDetailService;

	private final StatisticsService statisticsService;

	@Autowired
	public StatisticsResource(final ConnectionDetailService connectionDetailService, final StatisticsService statisticsService) {
		checkNotNull(connectionDetailService);
		checkNotNull(statisticsService);

		this.connectionDetailService = connectionDetailService;
		this.statisticsService = statisticsService;
	}

	/**
	 * Obtains table stats (rows and attribute count).
	 * 
	 * @param connectionDetailId connection ID
	 * @param table table
	 * @return table stats
	 */
	@GET
	@Path("connections/{connectionDetailId}/tables/{table}/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table statistics")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Stats obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table provided!") })
	@TypeHint(TableDto.StatisticsDto.class)
	public Response tableStatistics(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("table") String table) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final TableDto.StatisticsDto statisticsDto = this.statisticsService.tableStatistics(connectionDetail, null, table);

		return Response.ok(statisticsDto).build();
	}

	/**
	 * Obtains table stats (rows and attribute count).
	 * 
	 * @param connectionDetailId connection ID
	 * @param schema schema
	 * @param table table
	 * @return table stats
	 */
	@GET
	@Path("connections/{connectionDetailId}/schemas/{schema}/tables/{table}/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Table statistics")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Stats obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table or schema provided!") })
	@TypeHint(TableDto.StatisticsDto.class)
	public Response tableStatistics(final @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("schema") String schema, final @PathParam("table") String table) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final TableDto.StatisticsDto statisticsDto = this.statisticsService.tableStatistics(connectionDetail, schema, table);

		return Response.ok(statisticsDto).build();
	}
	
	/**
	 * Obtains stats (max, min, avg and median) of the named column.
	 * 
	 * @param connectionDetailId connection ID
	 * @param table table
	 * @param column column
	 * @return column stats
	 */
	@GET
	@Path("connections/{connectionDetailId}/tables/{table}/columns/{column}/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	@ResourceLabel("Column statistics")
	@StatusCodes({ @ResponseCode(code = 200, condition = "Stats obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table provided!") })
	@TypeHint(FieldDto.StatisticsDto.class)
	public Response columnStatistics(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("table") String table, final @PathParam("column") String column) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}
		
		final FieldDto.StatisticsDto statisticsDto = this.statisticsService.columnStatistics(connectionDetail, null, table, column);

		return Response.ok(statisticsDto).build();
	}

	/**
	 * Obtains stats (max, min, avg and median) of the named column.
	 * 
	 * @param connectionDetailId connection ID
	 * @param schema schema
	 * @param table table
	 * @param column column
	 * @return column stats
	 */
	@GET
	@Path("connections/{connectionDetailId}/schemas/{schema}/tables/{table}/columns/{column}/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	@StatusCodes({ @ResponseCode(code = 200, condition = "Stats obtained"),
		@ResponseCode(code = 404, condition = "Unknown connection"),
		@ResponseCode(code = 400, condition = "Invalid table or schema provided!") })
	@TypeHint(FieldDto.StatisticsDto.class)
	public Response columnStatistics(final @TypeHint(UUID.class) @PathParam("connectionDetailId") UUID connectionDetailId,
			final @PathParam("schema") String schema, final @PathParam("table") String table,
			final @PathParam("column") String column) {
		final ConnectionDetail connectionDetail = this.connectionDetailService.get(connectionDetailId);
		if (connectionDetail == null) {
			throw new NotFoundException("Unknown connection!");
		}		
		
		final FieldDto.StatisticsDto statisticsDto = this.statisticsService.columnStatistics(connectionDetail, schema, table, column);

		return Response.ok(statisticsDto).build();
	}
}
