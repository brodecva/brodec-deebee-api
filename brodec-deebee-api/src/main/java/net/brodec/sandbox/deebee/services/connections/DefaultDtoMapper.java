package net.brodec.sandbox.deebee.services.connections;

import java.util.UUID;

import org.springframework.stereotype.Component;

import net.brodec.sandbox.deebee.dtos.ConnectionDetailDto;

@Component
public class DefaultDtoMapper implements DtoMapper {

	@Override
	public ConnectionDetail to(ConnectionDetailDto connectionDetailDto) {
		return to(connectionDetailDto.getId(), connectionDetailDto);
	}

	@Override
	public ConnectionDetail to(UUID connectionDetailId, ConnectionDetailDto connectionDetailDto) {
		return ConnectionDetail
			.builder()
			.id(connectionDetailId)
			.name(connectionDetailDto.getName())
			.hostUrl(connectionDetailDto.getHostUrl())
			.database(connectionDetailDto.getDatabase())
			.schema(connectionDetailDto.getSchema())
			.username(connectionDetailDto.getUsername())
			.password(connectionDetailDto.getPassword())
			.vendorDialect(connectionDetailDto.getVendorDialect())
			.build();
	}

	@Override
	public ConnectionDetailDto to(ConnectionDetail connectionDetail) {
		final ConnectionDetailDto value = new ConnectionDetailDto();
		
		value.setId(connectionDetail.getId());
		value.setName(connectionDetail.getName());
		value.setHostUrl(connectionDetail.getHostUrl());
		value.setDatabase(connectionDetail.getDatabase());
		value.setSchema(connectionDetail.getSchema());
		value.setUsername(connectionDetail.getUsername());
		value.setPassword(connectionDetail.getPassword());
		value.setVendorDialect(connectionDetail.getVendorDialect());
		
		return value;
	}
}
