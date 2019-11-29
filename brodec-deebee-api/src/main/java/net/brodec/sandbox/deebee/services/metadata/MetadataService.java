package net.brodec.sandbox.deebee.services.metadata;

import java.util.List;
import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.SchemaDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;

public interface MetadataService {
	List<SchemaDto> schemas(ConnectionDetail connectionDetail);
	
	List<TableDto> tables(ConnectionDetail connectionDetail, String schemaName);
	
	List<FieldDto> columns(ConnectionDetail connectionDetail, String schemaName, String tableName);
	
	FieldDto column(ConnectionDetail connectionDetail, String schemaName, String tableName, String columnName);
}
