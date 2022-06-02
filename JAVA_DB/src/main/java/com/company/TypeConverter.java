package com.company;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

    @Converter(autoApply = true)
    public class TypeConverter implements AttributeConverter<UserType, String> {

        @Override
        public String convertToDatabaseColumn(UserType type) {
            return (type != null)? type.getType() : null;
        }


        @Override
        public UserType convertToEntityAttribute(String type) {
            return (type != null)? UserType.fromType(type) : null;
        }
    }
