package com.company.service;


import com.company.dto.UserDTO;
import com.company.dto.converter.UserDTOConverter;
import com.company.dto.request.CreateUserDTORequest;
import com.company.dto.request.UpdateUserDTORequest;
import com.company.exception.UserExistException;
import com.company.exception.UserNotFoundException;
import com.company.model.Role;
import com.company.model.User;
import com.company.model.UserRole;
import com.company.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;
    private final RoleService roleService;

    BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();


    public UserService(UserRepository userRepository,
                       UserDTOConverter userDTOConverter, RoleService roleService) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
        this.roleService = roleService;
    }


    public UserDTO createUser(CreateUserDTORequest createUserDTORequest) {

        validateUser(createUserDTORequest.getEmail(),
                createUserDTORequest.getUsername(), createUserDTORequest.getPhone());

        Role role = roleService.findRoleByName("USER");

        User user = new User();

        UserRole userRole = new UserRole();
        userRole.setRoleId(role);
        userRole.setUserId(user);

        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(userRole);

        user.setName(createUserDTORequest.getName());
        user.setSurname(createUserDTORequest.getSurname());
        user.setUsername(createUserDTORequest.getUsername());
        user.setName(createUserDTORequest.getName());
        user.setEmail(createUserDTORequest.getEmail());
        user.setPhoneNumber(createUserDTORequest.getPhone());
        user.setUserRoleList(userRoleList);
        user.setPassword(cryptPasswordEncoder.encode(createUserDTORequest.getPassword()));
        user.setIsActive(false);

        return userDTOConverter.convert(userRepository.save(user));
    }

    public List<UserDTO> getAllUser() {
        return userDTOConverter.convert(userRepository.findAll());
    }


    public UserDTO updateUser(UpdateUserDTORequest updateUserDTORequest) {

        if (userRepository.existsById(updateUserDTORequest.getId()) == false) {
            throw new UserNotFoundException("User couldn't be found by following id:");
        }

        validateUser(updateUserDTORequest.getEmail(),
                updateUserDTORequest.getUsername(), updateUserDTORequest.getPhone());

        User user = new User();
        user.setId(updateUserDTORequest.getId());
        user.setName(updateUserDTORequest.getName());
        user.setSurname(updateUserDTORequest.getSurname());
        user.setUsername(updateUserDTORequest.getUsername());
        user.setEmail(updateUserDTORequest.getEmail());
        user.setPhoneNumber(updateUserDTORequest.getPhone());
        user.setPassword(cryptPasswordEncoder.encode(updateUserDTORequest.getPassword()));

        return userDTOConverter.convert(userRepository.save(user));
    }


    private User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserDTO getUserById(Long id) {
        return userDTOConverter.convert(userRepository.getById(id));
    }


    private User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    public UserDTO findUserByUsername(String username) {
        return userDTOConverter.convert(userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User couldn't be found by following username: " + username)));
    }


    private void validateUser(String email, String username, String phoneNumber) {

        if (userRepository.existsByUsername(username))
            throw new UserExistException("User exist by following username");

        if (userRepository.existsByEmail(email))
            throw new UserExistException("User exist by following email");

        if (userRepository.existsByPhoneNumber(phoneNumber))
            throw new UserExistException("User exist by following phone number");
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deactiveUser(Long userId) {
        //TODO IN
    }

    public void activeUser(Long id) {
        //TODO IN
    }
}
