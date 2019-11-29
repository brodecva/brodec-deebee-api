package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class CharacterSetDto implements Serializable {
	
	private static final long serialVersionUID = 1034114773184584295L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CharacterSetDto [name=" + name + "]";
	}
}
