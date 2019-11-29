package net.brodec.sandbox.deebee.services.preview;

import net.brodec.sandbox.deebee.dtos.ResultDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;

public interface PreviewService {
	
	ResultDto preview(ConnectionDetail connectionDetail, String schemaName,
			String tableName, Integer offset, Integer limit);
}
