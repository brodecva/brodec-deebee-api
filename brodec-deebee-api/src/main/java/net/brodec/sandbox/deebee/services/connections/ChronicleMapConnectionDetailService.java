package net.brodec.sandbox.deebee.services.connections;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.UUID;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.openhft.chronicle.map.ChronicleMap;

@Service
public class ChronicleMapConnectionDetailService implements ConnectionDetailService {

	private static final String TEMP_FILE_PREFIX = "brodec-sbx-deebee-ch";
	private static final String TEMP_FILE_SUFFIX = ".tmp";
	
	private final ChronicleMap<UUID, ConnectionDetail> idsToDetails;
	
	@Autowired
	public ChronicleMapConnectionDetailService(
		final @Value("${net.brodec.sandbox.deebee.chronicle.maxEntries}") int maxEntries,
		final @Value("${net.brodec.sandbox.deebee.chronicle.persistenceFile:#{null}}") String persistenceFile
	) throws IOException {
		this.idsToDetails = ChronicleMap
				    .of(UUID.class, ConnectionDetail.class)
				    .averageKey(UUID.randomUUID())
				    .averageValue(ConnectionDetail.example())
				    .entries(maxEntries)
				    .createOrRecoverPersistedTo(getPersistenceFileOrDefault(persistenceFile), false);
	}
	
	private File getPersistenceFileOrDefault(final String persistenceFile) throws IOException {
		if (persistenceFile == null) {
			return Files.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX).toFile();
		} else {
			return new File(persistenceFile);
		}
	}
	
	@Override
	public ConnectionDetail get(final UUID connectionDetailId) {
		checkArgument(connectionDetailId != null);
		
		return this.idsToDetails.get(connectionDetailId);
	}

	@Override
	public boolean save(ConnectionDetail connectionDetail) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		
		return this.idsToDetails.putIfAbsent(connectionDetail.getId(), connectionDetail) == null;
	}

	@Override
	public boolean saveOrUpdate(ConnectionDetail connectionDetail) {
		checkArgument(connectionDetail != null, "No connection detail provided!");
		
		return this.idsToDetails.put(connectionDetail.getId(), connectionDetail) == null;
	}

	@Override
	public Collection<ConnectionDetail> list() {
		return this.idsToDetails.values();
	}

	@Override
	public boolean delete(UUID connectionDetailId) {
		checkArgument(connectionDetailId != null);
		
		return this.idsToDetails.remove(connectionDetailId) != null;
	}
	
	@PreDestroy
	  public void onExit() {
	    this.idsToDetails.close();
	}
}
