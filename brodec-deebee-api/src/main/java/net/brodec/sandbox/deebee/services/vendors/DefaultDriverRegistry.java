package net.brodec.sandbox.deebee.services.vendors;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Component
public class DefaultDriverRegistry implements DriverRegistry {

	private final Map<VendorDialect, Class<?>> vendorsToDrivers = Maps.immutableEnumMap(ImmutableMap.of(VendorDialect.POSTGRES, org.postgresql.Driver.class));
	
	@Override
	public String getDriverClassNameFor(final VendorDialect vendorDialect) {
		checkArgument(vendorDialect != null, "No vendor dialect provided!");
		
		final Class<?> driverClass = vendorsToDrivers.get(vendorDialect);
		if (driverClass == null) {
			return null;
		}
		
		return driverClass.getName();
	}

}
