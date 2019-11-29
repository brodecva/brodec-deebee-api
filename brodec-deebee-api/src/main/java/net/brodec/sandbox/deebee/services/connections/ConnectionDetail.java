package net.brodec.sandbox.deebee.services.connections;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.brodec.sandbox.deebee.api.rest.responses.ConnectionDetailAdapter;
import net.brodec.sandbox.deebee.services.vendors.VendorDialect;

@XmlJavaTypeAdapter(ConnectionDetailAdapter.class)
public final class ConnectionDetail implements Serializable {
	
	private static final long serialVersionUID = -2700351489850416450L;

	public static final class Builder {
		private UUID id;
		
		private String name;
		
		private String hostUrl;
		
		private String database;
		
		private String schema;
		
		private String username;
		
		private String password;
		
		private VendorDialect vendorDialect;
		
		public Builder id(final UUID id) {
			this.id = id;
			
			return this;
		}
		
		public Builder name(final String name) {
			this.name = name;
			
			return this;
		}
		
		public Builder hostUrl(final String hostUrl) {
			this.hostUrl = hostUrl;
			
			return this;
		}
		
		public Builder database(final String database) {
			this.database = database;
			
			return this;
		}
		
		public Builder schema(final String schema) {
			this.schema = schema;
			
			return this;
		}
		
		public Builder username(final String username) {
			this.username = username;
			
			return this;
		}
		
		public Builder password(final String password) {
			this.password = password;
			
			return this;
		}
		
		public Builder vendorDialect(final VendorDialect vendorDialect) {
			this.vendorDialect = vendorDialect;
			
			return this;
		}
		
		public ConnectionDetail build() {
			return new ConnectionDetail(this.id, this.name, this.hostUrl, this.database, this.schema, this.username, this.password, this.vendorDialect);
		}
	}
	
	private final UUID id;
	
	private final String name;
	
	private final String hostUrl;
	
	private final String database;
	
	private final String schema;
	
	private final String username;
	
	private final String password;
	
	private final VendorDialect vendorDialect;

	public static Builder builder() {
		return new Builder();
	}
	
	public ConnectionDetail(UUID id, String name, String hostUrl, String database, String schema, String username,
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

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public String getDatabase() {
		return database;
	}

	public String getSchema() {
		return schema;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public VendorDialect getVendorDialect() {
		return vendorDialect;
	}
	
	public static ConnectionDetail example() {
		return new ConnectionDetail(
				UUID.randomUUID(), "My connection", "jdbc:postgresql://localhost:5435/postgres", "postgres", "information_schema", "postgres", "secret", VendorDialect.POSTGRES
		);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConnectionDetail other = (ConnectionDetail) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ConnectionDetail [id=" + id + ", name=" + name + ", hostUrl=" + hostUrl + ", database=" + database
				+ ", schema=" + schema + ", username=" + username + ", password=" + password + ", vendorDialect="
				+ vendorDialect + "]";
	}
}
