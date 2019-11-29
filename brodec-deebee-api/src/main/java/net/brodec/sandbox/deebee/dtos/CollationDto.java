package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class CollationDto implements Serializable {
	private static final long serialVersionUID = 7948397504165193863L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CollationDto [name=" + name + "]";
	}
}
