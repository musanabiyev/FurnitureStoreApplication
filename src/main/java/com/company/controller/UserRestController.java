package com.company.controller;

import com.company.dto.UserDTO;
import com.company.dto.request.CreateUserDTORequest;
import com.company.dto.request.UpdateUserDTORequest;
import com.company.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserDTORequest createUserDTORequest) {
        return ResponseEntity.ok(userService.createUser(createUserDTORequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserDTORequest updateUserDTORequest) {
        return ResponseEntity.ok(userService.updateUser(updateUserDTORequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id", required = true) Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(
            @PathVariable(value = "username", required = true) String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id", required = true) Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/deactiveuser/{id}")
    public ResponseEntity<Void> deactiveUser(@PathVariable(value = "id", required = true) Long userId) {
        userService.deactiveUser(userId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/activeuser/{id}")
    public ResponseEntity<Void> activeUser(@PathVariable(value = "id", required = true) Long id) {
        userService.activeUser(id);
        return ResponseEntity.ok().build();
    }

}
