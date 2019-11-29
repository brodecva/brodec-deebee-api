package net.brodec.sandbox.deebee.services.connections;

import java.util.UUID;

import net.brodec.sandbox.deebee.dtos.ConnectionDetailDto;

public interface DtoMapper {
	ConnectionDetail to(final ConnectionDetailDto connectionDetailDto);
	ConnectionDetail to(final UUID connectionDetailId, final ConnectionDetailDto connectionDetailDto);
	ConnectionDetailDto to(final ConnectionDetail connectionDetail);
}
