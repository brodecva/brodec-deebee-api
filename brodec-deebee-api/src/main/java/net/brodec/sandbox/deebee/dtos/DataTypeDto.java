package net.brodec.sandbox.deebee.dtos;

import java.io.Serializable;

public class DataTypeDto implements Serializable {
	
	private static final long serialVersionUID = 6150764000364009843L;

	private String castTypeName;
	
	private String sqlDataType;
	
	private String typeName;
	
	private CharacterSetDto characterSet;
	
	private CollationDto collation;
	
	private FieldDto defaultValue;
	
	private boolean defaulted;
	
	private boolean identity;
	
	private boolean array;
	
	private boolean binary;
	
	private boolean date;
	
	private boolean dateTime;
	
	private boolean isEnum;
	
	private boolean interval;
	
	private boolean lob;
	
	private boolean numeric;
	
	private boolean string;
	
	private boolean temporal;
	
	private boolean time;
	
	private boolean timestamp;
	
	private boolean udt;
	
	private boolean hasLength;
	
	private boolean hasPrecision;
	
	private boolean hasScale;
	
	private int length;
	
	private int precision;
	
	private int scale;
	
	private NullabilityDto nullability;

	public String getCastTypeName() {
		return castTypeName;
	}

	public void setCastTypeName(String castTypeName) {
		this.castTypeName = castTypeName;
	}

	public String getSqlDataType() {
		return sqlDataType;
	}

	public void setSqlDataType(String sqlDataType) {
		this.sqlDataType = sqlDataType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public CharacterSetDto getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(CharacterSetDto characterSet) {
		this.characterSet = characterSet;
	}

	public CollationDto getCollation() {
		return collation;
	}

	public void setCollation(CollationDto collation) {
		this.collation = collation;
	}

	public FieldDto getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(FieldDto defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isDefaulted() {
		return defaulted;
	}

	public void setDefaulted(boolean defaulted) {
		this.defaulted = defaulted;
	}

	public boolean isIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}

	public boolean isBinary() {
		return binary;
	}

	public void setBinary(boolean binary) {
		this.binary = binary;
	}

	public boolean isDate() {
		return date;
	}

	public void setDate(boolean date) {
		this.date = date;
	}

	public boolean isDateTime() {
		return dateTime;
	}

	public void setDateTime(boolean dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}

	public boolean isInterval() {
		return interval;
	}

	public void setInterval(boolean interval) {
		this.interval = interval;
	}

	public boolean isLob() {
		return lob;
	}

	public void setLob(boolean lob) {
		this.lob = lob;
	}

	public boolean isNumeric() {
		return numeric;
	}

	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}

	public boolean isString() {
		return string;
	}

	public void setString(boolean string) {
		this.string = string;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
	}

	public boolean isTimestamp() {
		return timestamp;
	}

	public void setTimestamp(boolean timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isUdt() {
		return udt;
	}

	public void setUdt(boolean udt) {
		this.udt = udt;
	}

	public boolean isHasLength() {
		return hasLength;
	}

	public void setHasLength(boolean hasLength) {
		this.hasLength = hasLength;
	}

	public boolean isHasPrecision() {
		return hasPrecision;
	}

	public void setHasPrecision(boolean hasPrecision) {
		this.hasPrecision = hasPrecision;
	}

	public boolean isHasScale() {
		return hasScale;
	}

	public void setHasScale(boolean hasScale) {
		this.hasScale = hasScale;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public NullabilityDto getNullability() {
		return nullability;
	}

	public void setNullability(NullabilityDto nullability) {
		this.nullability = nullability;
	}

	@Override
	public String toString() {
		return "DataTypeDto [castTypeName=" + castTypeName + ", sqlDataType=" + sqlDataType + ", typeName=" + typeName
				+ ", characterSet=" + characterSet + ", collation=" + collation + ", defaultValue=" + defaultValue
				+ ", defaulted=" + defaulted + ", identity=" + identity
				+ ", array=" + array + ", binary=" + binary + ", date=" + date + ", dateTime=" + dateTime + ", isEnum="
				+ isEnum + ", interval=" + interval + ", lob=" + lob + ", numeric=" + numeric + ", string=" + string
				+ ", temporal=" + temporal + ", time=" + time + ", timestamp=" + timestamp + ", udt=" + udt
				+ ", hasLength=" + hasLength + ", hasPrecision=" + hasPrecision + ", hasScale=" + hasScale + ", length="
				+ length + ", precision=" + precision + ", scale=" + scale + ", nullability=" + nullability + "]";
	}
}
