package net.brodec.sandbox.deebee.services.preview;

import static org.jooq.impl.DSL.*;

import java.util.Arrays;

import org.jooq.Record;
import org.jooq.Result;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.brodec.sandbox.deebee.dtos.ResultDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.jooq.DSLContextFactory;
import net.brodec.sandbox.deebee.services.jooq.DtoMapper;

@Service
public class JooqPreviewService implements PreviewService {

	private final DSLContextFactory dslContextFactory;

	private final DtoMapper dtoMapper;

	@Autowired
	public JooqPreviewService(final DSLContextFactory dslContextFactory, final DtoMapper dtoMapper) {
		checkNotNull(dslContextFactory);
		checkNotNull(dtoMapper);

		this.dslContextFactory = dslContextFactory;
		this.dtoMapper = dtoMapper;
	}

	@Override
	public ResultDto preview(ConnectionDetail connectionDetail, String schemaName, String tableName, Integer offset,
			Integer limit) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		checkArgument(tableName != null, "No table name provided!");
		checkArgument(limit == null || limit >= 0, "The limit must be nonnegative!");
		checkArgument(offset == null || offset >= 0, "The offset must be nonnegative!");

		final Result<Record> result = dslContextFactory.create(connectionDetail).select()
				.from(table(name(schemaName, tableName))).limit(limit).offset(offset).fetch();
		
		return new ResultDto(this.dtoMapper.fieldsToFieldDtos(Arrays.asList(result.fields())), result.intoArrays());
	}
}
