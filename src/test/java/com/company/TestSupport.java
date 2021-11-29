package com.company;

import com.company.dto.RoleDTO;
import com.company.model.Role;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    private static int roleId = 100;

    public static List<Role> generateRoles(){
        return IntStream.range(0,4).mapToObj(i ->
            new Role(i, i + "Role")).collect(Collectors.toList());
    }

    public static List<RoleDTO> generateRoleDTOList(List<Role> roleList){

        return roleList.stream()
                .map(from -> new RoleDTO(from.getId(), from.getName())).collect(Collectors.toList());
    }

    public static Role generateRole(String name){
        return new Role(roleId,
                roleId + "user");
    }

    public static RoleDTO generateRole(){
        return new RoleDTO(roleId, "user" + roleId);
    }
}
