package net.brodec.sandbox.deebee.services.connections;

import java.util.Collection;
import java.util.UUID;

public interface ConnectionDetailService {
	ConnectionDetail get(UUID connectionDetailId);
	
	boolean save(ConnectionDetail connectionDetail);
	
	boolean saveOrUpdate(ConnectionDetail connectionDetail);
	
	Collection<ConnectionDetail> list();
	
	boolean delete(UUID connectionDetailId);
}
