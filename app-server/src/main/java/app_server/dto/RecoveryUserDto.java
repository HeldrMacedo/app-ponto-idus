package app_server.dto;

import java.util.List;

import app_server.entity.Role;

public record RecoveryUserDto(
		Long id,
        String email,
        List<Role> roles
        ) {}
