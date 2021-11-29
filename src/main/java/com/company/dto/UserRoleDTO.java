package com.company.dto;

import com.company.model.Role;
import com.company.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleDTO {

    private Integer id;
    private Role roleId;
    private User userId;


    public UserRoleDTO(Integer id, Role roleId, User userId) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
    }
}
