package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.models.Address;
import com.ecommerce.user.models.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public Optional<UserResponse> getById(Long id) {
        return userRepository.findById(id).map(this::toResponse);
    }

    public UserResponse create(UserRequest request) {
        return save(new User(), request);
    }

    public Optional<UserResponse> update(Long id, UserRequest request) {
        return userRepository.findById(id).map(user -> save(user, request));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse save(User user, UserRequest request) {
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setAddress(toAddress(request.address()));
        return toResponse(userRepository.save(user));
    }

    private Address toAddress(AddressDTO dto) {
        if (dto == null) return null;
        Address address = new Address();
        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setCountry(dto.country());
        address.setZipCode(dto.zipCode());
        return address;
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhone(), user.getRole(), toAddressDTO(user.getAddress()));
    }

    private AddressDTO toAddressDTO(Address address) {
        if (address == null) return null;
        return new AddressDTO(address.getStreet(), address.getCity(), address.getState(), address.getCountry(),
                address.getZipCode());
    }

}
