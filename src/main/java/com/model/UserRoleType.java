package com.model;

public enum UserRoleType {
    USER("USER"),
    ENTERPRISE("ENTERPRISE"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userRoleType;

    UserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getRole() {
        return userRoleType;
    }
}
