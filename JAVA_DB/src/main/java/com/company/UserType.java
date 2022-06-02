package com.company;

public enum UserType {
    ADMIN("ADMIN"),
    REGULAR("REGULAR");


    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public static UserType fromType(String type) {
        if (type.equals(ADMIN.type)) {
            return ADMIN;
        } else if (type.equals(REGULAR.type)) {
            return REGULAR;
        } else {
            return null;
        }
    }

    public String getType() {
        return type;
    }


}
//package com.company;
//
//public enum UserType {
//    ADMIN("ADMIN"),
//    REGULAR("REGULAR");
//
//        private final String type;
//
//    UserType(String type) {
//        this.type = type;
//    }
//}
