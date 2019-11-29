package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;
import java.util.UUID;

import com.webcohesion.enunciate.metadata.rs.TypeHint;

import net.brodec.sandbox.deebee.services.vendors.VendorDialect;

public class ConnectionDetailDto implements Serializable {
	
	private static final long serialVersionUID = 252962048951816740L;

	private UUID id;
	
	private String name;
	
	private String hostUrl;
	
	private String database;
	
	private String schema;
	
	private String username;
	
	private String password;
	
	private VendorDialect vendorDialect;

	public ConnectionDetailDto(UUID id, String name, String hostUrl, String database, String schema, String username,
			String password, VendorDialect vendorDialect) {
		this.id = id;
		this.name = name;
		this.hostUrl = hostUrl;
		this.database = database;
		this.schema = schema;
		this.username = username;
		this.password = password;
		this.vendorDialect = vendorDialect;
	}

	public ConnectionDetailDto() {
	}

	@TypeHint(UUID.class)
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@TypeHint(VendorDialect.class)
	public VendorDialect getVendorDialect() {
		return vendorDialect;
	}

	public void setVendorDialect(VendorDialect vendorDialect) {
		this.vendorDialect = vendorDialect;
	}

	@Override
	public String toString() {
		return "ConnectionDetailDto [id=" + id + ", name=" + name + ", hostUrl=" + hostUrl + ", database=" + database
				+ ", schema=" + schema + ", username=" + username + ", password=" + password + ", vendorDialect="
				+ vendorDialect + "]";
	}
}
