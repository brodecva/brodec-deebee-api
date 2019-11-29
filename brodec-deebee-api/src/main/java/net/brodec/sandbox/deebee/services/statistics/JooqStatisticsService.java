package net.brodec.sandbox.deebee.services.statistics;

import static org.jooq.impl.DSL.*;

import java.math.BigDecimal;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Result;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.dtos.TableDto.StatisticsDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.jooq.DSLContextFactory;
import net.brodec.sandbox.deebee.services.metadata.MetadataService;

@Service
public class JooqStatisticsService implements StatisticsService {

	private final DSLContextFactory dslContextFactory;

	private final MetadataService metadataService;

	@Autowired
	public JooqStatisticsService(final DSLContextFactory dslContextFactory, final MetadataService metadataService) {
		checkNotNull(dslContextFactory);
		checkNotNull(metadataService);

		this.dslContextFactory = dslContextFactory;
		this.metadataService = metadataService;
	}

	@Override
	public StatisticsDto tableStatistics(ConnectionDetail connectionDetail, String schemaName, String tableName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		checkArgument(tableName != null, "No table name provided!");
		
		final Result<Record1<Integer>> recordsCountResult = dslContextFactory.create(connectionDetail).select(count())
				.from(table(name(schemaName, tableName))).fetch();
		
		final Result<Record> emptyResult = dslContextFactory.create(connectionDetail).select()
				.from(table(name(schemaName, tableName))).where(falseCondition()).limit(0).fetch();
		
		return new TableDto.StatisticsDto(recordsCountResult.get(0).get(0, Integer.class), emptyResult.fields().length);
	}

	@Override
	public net.brodec.sandbox.deebee.dtos.FieldDto.StatisticsDto columnStatistics(ConnectionDetail connectionDetail,
			String schemaName, String tableName, String columnName) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		checkArgument(tableName != null, "No table name provided!");
		checkArgument(columnName != null, "No column name provided!");
		
		final boolean columnNumeric = isNumeric(connectionDetail, schemaName, tableName, columnName);
		
		final Result<Record4<Object, Object, BigDecimal, BigDecimal>> result = dslContextFactory.create(connectionDetail).select(
					min(field(name(columnName))),
					min(field(name(columnName))),
					columnNumeric ? avg(field(name(columnName), BigDecimal.class)) : val(null, BigDecimal.class),
					columnNumeric ? median(field(name(columnName), BigDecimal.class)) : val(null, BigDecimal.class)
				)
				.from(table(name(schemaName, tableName))).fetch();
		
		final Record4<Object, Object, BigDecimal, BigDecimal> resultRecord = result.get(0);
		
		return new FieldDto.StatisticsDto(resultRecord.get(0), resultRecord.get(1), resultRecord.get(2), resultRecord.get(3));
	}

	private boolean isNumeric(ConnectionDetail connectionDetail, String schemaName, String tableName,
			String columnName) {
		return this.metadataService.column(connectionDetail, schemaName, tableName, columnName).getDataType().isNumeric();
	}
}
