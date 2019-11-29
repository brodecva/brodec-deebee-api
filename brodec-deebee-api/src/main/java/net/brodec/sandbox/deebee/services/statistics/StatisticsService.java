package net.brodec.sandbox.deebee.services.statistics;

import org.springframework.stereotype.Service;

import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;

@Service
public interface StatisticsService {
	
	TableDto.StatisticsDto tableStatistics(ConnectionDetail connectionDetail, String schemaName,
			String tableName);
	
	FieldDto.StatisticsDto columnStatistics(ConnectionDetail connectionDetail, String schemaName,
			String tableName, String columnName);
}
