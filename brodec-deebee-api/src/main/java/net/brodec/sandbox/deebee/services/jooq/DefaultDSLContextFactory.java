package net.brodec.sandbox.deebee.services.jooq;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.brodec.sandbox.deebee.services.connections.ConnectionDetail;
import net.brodec.sandbox.deebee.services.connections.DataSourceFactory;
import net.brodec.sandbox.deebee.services.vendors.VendorDialect;

@Component
public class DefaultDSLContextFactory implements DSLContextFactory {

	private final Map<VendorDialect, SQLDialect> vendorToJooq = Maps.immutableEnumMap(ImmutableMap.of(VendorDialect.POSTGRES, SQLDialect.POSTGRES));
	
	private final DataSourceFactory dataSourceFactory;
	
	@Autowired
	public DefaultDSLContextFactory(final DataSourceFactory dataSourceFactory) {
		checkNotNull(dataSourceFactory);
		
		this.dataSourceFactory = dataSourceFactory;
	}
	
	@Override
	public DSLContext create(final ConnectionDetail connectionDetail) {
		final SQLDialect dialect = toJooqDialect(connectionDetail.getVendorDialect());
		
		final DataSource dataSource = this.dataSourceFactory.create(connectionDetail);
		
		return DSL.using(dataSource, dialect);
	}

	private SQLDialect toJooqDialect(final VendorDialect vendorDialect) {
		final SQLDialect dialect = this.vendorToJooq.get(vendorDialect);
		
		checkArgument(dialect != null, String.format("Unsupported dialect %s!", vendorDialect.name()));
		
		return dialect;
	}
}
