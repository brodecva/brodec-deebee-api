package net.brodec.sandbox.deebee.services.metadata;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.jooq.impl.DSL.name;

import java.util.Arrays;
import java.util.List;
import org.jooq.Meta;
import org.jooq.Schema;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.SchemaDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.jooq.DSLContextFactory;
import net.brodec.sandbox.deebee.services.jooq.DtoMapper;

@Service
public class JooqMetadataService implements MetadataService {

	private final DSLContextFactory dslContextFactory;
	
	private final DtoMapper dtoMapper;
	
	@Autowired
	public JooqMetadataService(final DSLContextFactory dslContextFactory, final DtoMapper dtoMapper) {
		checkNotNull(dslContextFactory);
		checkNotNull(dtoMapper);
		
		this.dslContextFactory = dslContextFactory;
		this.dtoMapper = dtoMapper;
	}
	
	@Override
	public List<SchemaDto> schemas(final ConnectionDetail connectionDetail) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		
		return this.dtoMapper.schemasToSchemaDtos(meta(connectionDetail).getSchemas());
	}

	@Override
	public List<TableDto> tables(final ConnectionDetail connectionDetail, String schemaName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		
		final Meta meta = meta(connectionDetail);
		
		if (schemaName == null) {
			return this.dtoMapper.tablesToTableDtos(meta.getTables());
		}
		
		final List<Schema> matchedSchemas = meta.getSchemas(name(schemaName));
		
		checkArgument(!matchedSchemas.isEmpty(), String.format("No schema named %s found for the data source!", schemaName));
		checkArgument(matchedSchemas.size() <= 1, String.format("More than one schema named %s present in the data source!", schemaName));
		
		return this.dtoMapper.tablesToTableDtos(matchedSchemas.get(0).getTables());
	}

	@Override
	public List<FieldDto> columns(final ConnectionDetail connectionDetail, String schemaName, String tableName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		checkArgument(tableName != null, "No table name provided!");
		
		final Table<?> table = table(connectionDetail, schemaName, tableName);
		
		return this.dtoMapper.fieldsToFieldDtos(Arrays.asList(table.fields()));
	}

	@Override
	public FieldDto column(final ConnectionDetail connectionDetail, String schemaName, String tableName, String columnName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		checkArgument(tableName != null, "No table name provided!");
		checkArgument(columnName != null, "No column name provided!");
		
		final Table<?> table = table(connectionDetail, schemaName, tableName);
		
		return this.dtoMapper.to(table.field(columnName));
	}

	private Table<?> table(final ConnectionDetail connectionDetail, String schemaName, String tableName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		final Meta meta = meta(connectionDetail);
		
		final List<Table<?>> matchedTables;
		if (schemaName == null) {
			matchedTables = meta.getTables(name(tableName));
			
			checkArgument(!matchedTables.isEmpty(), String.format("No table named %s found for the data source!", tableName));
			checkArgument(matchedTables.size() <= 1, String.format("More than one table named %s present in the data source!", tableName));
		} else {
			matchedTables = meta.getTables(name(schemaName, tableName));
			
			checkArgument(!matchedTables.isEmpty(), String.format("No table named %s found in the schema %s for the data source!", tableName, schemaName));
			checkArgument(matchedTables.size() <= 1, String.format("More than one table named %s present in the schema %s in the data source!", tableName, schemaName));
		}
		
		return matchedTables.get(0);
	}
	
	private Meta meta(final ConnectionDetail connectionDetail) {
		return dslContextFactory.create(connectionDetail).meta();
	}
}
