package com.company.dto.converter;

import com.company.dto.RoleDTO;
import com.company.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDTOConverter {

    public RoleDTO convert(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    public List<RoleDTO> convert(List<Role> roleList) {
        return roleList.stream().map(from -> new RoleDTO(from.getId(), from.getName()))
                .collect(Collectors.toList());
    }
}
