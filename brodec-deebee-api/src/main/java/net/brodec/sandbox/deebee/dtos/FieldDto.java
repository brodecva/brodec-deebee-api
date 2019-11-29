package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class FieldDto implements Serializable {
	
	public static class StatisticsDto implements Serializable {

		private static final long serialVersionUID = -8605877297287391259L;

		private Object minimum;
		
		private Object maximum;
		
		private Object average;
		
		private Object median;

		public StatisticsDto(Object minimum, Object maximum, Object average, Object median) {
			this.minimum = minimum;
			this.maximum = maximum;
			this.average = average;
			this.median = median;
		}

		public Object getMinimum() {
			return minimum;
		}

		public void setMinimum(Object minimum) {
			this.minimum = minimum;
		}

		public Object getMaximum() {
			return maximum;
		}

		public void setMaximum(Object maximum) {
			this.maximum = maximum;
		}

		public Object getAverage() {
			return average;
		}

		public void setAverage(Object average) {
			this.average = average;
		}

		public Object getMedian() {
			return median;
		}

		public void setMedian(Object median) {
			this.median = median;
		}

		@Override
		public String toString() {
			return "FieldDto.StatisticsDto [minimum=" + minimum + ", maximum=" + maximum + ", average=" + average + ", median="
					+ median + "]";
		}
	}
	
	private static final long serialVersionUID = -6606931765608391840L;

	private String comment;
	
	private DataTypeDto dataType;
	
	private String name;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DataTypeDto getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeDto dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FieldDto [comment=" + comment + ", dataType=" + dataType + ", name=" + name + "]";
	}
}
