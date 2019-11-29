package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class SchemaDto implements Serializable {
	
	private static final long serialVersionUID = 1220862503329177328L;

	private String comment;
	
	private String name;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SchemaDto [comment=" + comment + ", name=" + name + "]";
	}
}
