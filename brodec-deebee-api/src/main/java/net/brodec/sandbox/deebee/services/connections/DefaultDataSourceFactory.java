package net.brodec.sandbox.deebee.services.connections;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import net.brodec.sandbox.deebee.services.vendors.DriverRegistry;

@Component
public class DefaultDataSourceFactory implements DataSourceFactory {

	private final DriverRegistry driverRegistry;
	
	public DefaultDataSourceFactory(final DriverRegistry driverRegistry) {
		checkNotNull(driverRegistry);
		
		this.driverRegistry = driverRegistry;
	}
	
	@Override
	public DataSource create(ConnectionDetail connectionDetail) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		
		final String driverClassName = this.driverRegistry.getDriverClassNameFor(connectionDetail.getVendorDialect());
		
		final DriverManagerDataSource source = new DriverManagerDataSource();
        
        source.setDriverClassName(driverClassName);
        source.setUrl(connectionDetail.getHostUrl().toString());
        source.setCatalog(connectionDetail.getDatabase());
        source.setSchema(connectionDetail.getSchema());
        source.setUsername(connectionDetail.getUsername());
        source.setPassword(connectionDetail.getPassword());
        
        return source;
	}

}
