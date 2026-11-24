package com.ecommerce.user.dto;

import com.ecommerce.user.models.UserRole;

public record UserResponse(Long id, String firstName, String lastName, String email, String phone, UserRole role,
		AddressDTO address) {
}
