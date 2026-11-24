package com.ecommerce.user.controllers;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public List<UserResponse> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
		return ResponseEntity.of(userService.getById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@RequestBody UserRequest request) {
		return userService.create(request);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request) {
		return ResponseEntity.of(userService.update(id, request));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

}
