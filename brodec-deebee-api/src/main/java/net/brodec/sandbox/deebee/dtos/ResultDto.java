package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultDto implements Serializable {
	
	private static final long serialVersionUID = 8678440071467679955L;

	private List<FieldDto> fields;
	
	private Object[][] arrays;
	
	public ResultDto() { }
	
	public ResultDto(final List<? extends FieldDto> fields, final Object[][] arrays) {
		this.fields = new ArrayList<>(fields);
		this.arrays = arrays;
	}

	public List<FieldDto> getFields() {
		return fields;
	}

	public void setFields(List<? extends FieldDto> fields) {
		this.fields = new ArrayList<>(fields);
	}

	public Object[][] getArrays() {
		return arrays;
	}

	public void setArray(Object[][] arrays) {
		this.arrays = arrays;
	}

	@Override
	public String toString() {
		return "ResultDto [fields=" + fields + ", arrays=" + Arrays.toString(arrays) + "]";
	}	
}
