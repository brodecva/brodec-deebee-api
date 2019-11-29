package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class TableDto implements Serializable {
	
	public static class StatisticsDto implements Serializable {

		private static final long serialVersionUID = 355970044168496991L;
		
		private int recordsCount;
		
		private int attributesCount;

		public StatisticsDto(int recordsCount, int attributesCount) {
			this.recordsCount = recordsCount;
			this.attributesCount = attributesCount;
		}

		public int getRecordsCount() {
			return recordsCount;
		}

		public void setRecordsCount(int recordsCount) {
			this.recordsCount = recordsCount;
		}

		public int getAttributesCount() {
			return attributesCount;
		}

		public void setAttributesCount(int attributesCount) {
			this.attributesCount = attributesCount;
		}

		@Override
		public String toString() {
			return "TableDto.StatisticsDto [recordsCount=" + recordsCount + ", attributesCount=" + attributesCount + "]";
		}
	}
	
	private static final long serialVersionUID = -5679746550922792986L;

	private String comment;
	
	private DataTypeDto dataType;
	
	private IdentityDto identity;
	
	private String name;
	
	private UniqueKeyDto primaryKey;

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

	public IdentityDto getIdentity() {
		return identity;
	}

	public void setIdentity(IdentityDto identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UniqueKeyDto getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UniqueKeyDto primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String toString() {
		return "TableDto [comment=" + comment + ", dataType=" + dataType + ", identity=" + identity + ", name=" + name
				+ ", primaryKey=" + primaryKey + "]";
	}
}
