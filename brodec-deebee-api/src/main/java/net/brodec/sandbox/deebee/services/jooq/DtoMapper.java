package net.brodec.sandbox.deebee.services.jooq;

import net.brodec.sandbox.deebee.dtos.CharacterSetDto;
import net.brodec.sandbox.deebee.dtos.CollationDto;
import net.brodec.sandbox.deebee.dtos.DataTypeDto;
import net.brodec.sandbox.deebee.dtos.FieldDto;
import net.brodec.sandbox.deebee.dtos.IdentityDto;
import net.brodec.sandbox.deebee.dtos.NullabilityDto;
import net.brodec.sandbox.deebee.dtos.SchemaDto;
import net.brodec.sandbox.deebee.dtos.TableDto;
import net.brodec.sandbox.deebee.dtos.UniqueKeyDto;

import java.util.List;

import org.jooq.CharacterSet;
import org.jooq.Collation;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Nullability;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.UniqueKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {

	CollationDto to(Collation collation);
	
	@Mapping(source = "UDT", target = "udt")
	@Mapping(target = "sqlDataType", expression = "java( ( dataType.getSQLDataType() == null ? null : dataType.getSQLDataType().getTypeName() ) )")
	@Mapping(target = "characterSet", expression = "java( to( dataType.characterSet() ) )")
	@Mapping(target = "collation", expression = "java( to( dataType.collation() ) )")
	@Mapping(target = "defaultValue", expression = "java( to( dataType.defaultValue() ) )")
	@Mapping(target = "defaulted", expression = "java( dataType.defaulted() )")
	@Mapping(target = "identity", expression = "java( dataType.identity() )")
	@Mapping(target = "hasLength", expression = "java( dataType.hasLength() )")
	@Mapping(target = "hasPrecision", expression = "java( dataType.hasPrecision() )")
	@Mapping(target = "hasScale", expression = "java( dataType.hasScale() )")
	@Mapping(target = "length", expression = "java( dataType.length() )")
	@Mapping(target = "precision", expression = "java( dataType.precision() )")
	@Mapping(target = "scale", expression = "java( dataType.scale() )")
	@Mapping(target = "nullability", expression = "java( to( dataType.nullability() ) )")
	DataTypeDto to(DataType<?> dataType);
	
	FieldDto to(Field<?> field);
	List<FieldDto> fieldsToFieldDtos(List<? extends Field<?>> fields);
	CharacterSetDto to(CharacterSet characterSet);
	IdentityDto to(Identity<?, ?> identity);
	NullabilityDto to(Nullability nullability);
	SchemaDto to(Schema schema);
	List<SchemaDto> schemasToSchemaDtos(List<? extends Schema> schemas);
	TableDto to(Table<?> table);
	List<TableDto> tablesToTableDtos(List<? extends Table<?>> tables);
	UniqueKeyDto to(UniqueKey<?> uniqueKey);
}
