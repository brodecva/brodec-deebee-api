package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class IdentityDto implements Serializable {
	
	private static final long serialVersionUID = 3096171388859163128L;
	
	private FieldDto field;

	public FieldDto getField() {
		return field;
	}

	public void setField(FieldDto field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "IdentityDto [field=" + field + "]";
	}
}
