package com.company.dto.converter;

import com.company.dto.UserRoleDTO;
import com.company.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleDTOConverter {

    public UserRoleDTO convert(UserRole from) {
        return new UserRoleDTO(from.getId(), from.getRoleId(), from.getUserId());
    }
}
