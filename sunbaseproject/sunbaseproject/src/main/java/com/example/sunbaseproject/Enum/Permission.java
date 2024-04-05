package com.example.sunbaseproject.Enum;



import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CLIENT_READ("management:read"),
    CLIENT_UPDATE("management:update"),
    CLIENT_CREATE("management:create"),
    CLIENT_DELETE("management:delete"),
    USER_READ("management:read"),
    USER_UPDATE("management:update"),
    USER_CREATE("management:create"),
    USER_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}