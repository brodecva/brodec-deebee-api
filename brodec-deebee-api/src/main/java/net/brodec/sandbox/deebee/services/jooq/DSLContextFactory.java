package net.brodec.sandbox.deebee.services.jooq;

import org.jooq.DSLContext;

import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;

public interface DSLContextFactory {
	DSLContext create(final ConnectionDetail connectionDetail);
}
