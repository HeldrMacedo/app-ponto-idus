package app_server.dto;

import app_server.enums.RoleName;

public record CreateUserDto(
		String email,
		String name,
        String password,
        RoleName role
        ) {}
