package net.brodec.sandbox.deebee.api.rest.responses;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.brodec.sandbox.deebee.dtos.ConnectionDetailDto;
import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;

public final class ConnectionDetailAdapter extends XmlAdapter<ConnectionDetailDto, ConnectionDetail> {

	@Override
	public ConnectionDetailDto marshal(final ConnectionDetail bound) throws Exception {
		final ConnectionDetailDto value = new ConnectionDetailDto();
		
		value.setId(bound.getId());
		value.setName(bound.getName());
		value.setHostUrl(bound.getHostUrl());
		value.setDatabase(bound.getDatabase());
		value.setSchema(bound.getSchema());
		value.setUsername(bound.getUsername());
		value.setPassword(bound.getPassword());
		value.setVendorDialect(bound.getVendorDialect());
		
		return value;
	}

	@Override
	public ConnectionDetail unmarshal(final ConnectionDetailDto value) throws Exception {
		throw new UnsupportedOperationException("It is not allowed to automatically unmarshal connection details from DTOs!");
	}
}
