package com.ecommerce.user.dto;

public record UserRequest(String firstName, String lastName, String email, String phone, AddressDTO address) {
}
