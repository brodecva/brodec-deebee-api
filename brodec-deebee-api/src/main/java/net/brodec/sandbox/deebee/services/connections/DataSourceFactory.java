package net.brodec.sandbox.deebee.services.connections;

import javax.sql.DataSource;

public interface DataSourceFactory {
	DataSource create(ConnectionDetail connectionDetail);
}
