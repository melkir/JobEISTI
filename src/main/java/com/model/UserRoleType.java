package com.model;

public enum UserRoleType {
    USER("ROLE_USER"),
    ENTERPRISE("ROLE_ENTERPRISE"),
    DBA("ROLE_DBA"),
    ADMIN("ROLE_ADMIN");

    String userRoleType;

    UserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getRole() {
        return userRoleType;
    }
}
