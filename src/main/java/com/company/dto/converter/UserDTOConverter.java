package com.company.dto.converter;

import com.company.dto.UserDTO;
import com.company.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOConverter {
    public UserDTO convert(User from) {
        return new UserDTO(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getUsername(),
                from.getEmail(),
                from.getPhoneNumber(),
                from.getIsActive());
    }

    public List<UserDTO> convert(List<User> fromList) {

        return fromList.stream().map(from -> new UserDTO(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getUsername(),
                from.getEmail(),
                from.getPhoneNumber(),
                from.getIsActive())).collect(Collectors.toList());
    }
}
