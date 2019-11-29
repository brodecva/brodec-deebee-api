package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class UniqueKeyDto implements Serializable {
	
	private static final long serialVersionUID = 1600518376577048763L;

	private String name;
	
	private boolean primary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	@Override
	public String toString() {
		return "UniqueKeyDto [name=" + name + ", primary=" + primary + "]";
	}
}
