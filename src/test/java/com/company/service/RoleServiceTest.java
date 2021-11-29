package com.company.service;

import com.company.TestSupport;
import com.company.dto.RoleDTO;
import com.company.dto.converter.RoleDTOConverter;
import com.company.model.Role;
import com.company.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleServiceTest extends TestSupport {

    private RoleRepository roleRepository;
    private RoleService roleService;
    private RoleDTOConverter roleDTOConverter;

    @BeforeEach
    public void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleDTOConverter = Mockito.mock(RoleDTOConverter.class);
        roleService = new RoleService(roleRepository, roleDTOConverter);

    }


    @Test
    void createRole() {
    }

    @Test
    void updateRole() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void testFindAllRole_itShouldReturnRoleDTOList() {

        List<Role> roleList = generateRoles();
        List<RoleDTO> roleDTOList = generateRoleDTOList(roleList);

        Mockito.when(roleRepository.findAll()).thenReturn(roleList);
        Mockito.when(roleDTOConverter.convert(roleList)).thenReturn(roleDTOList);

        List<RoleDTO> result = roleService.findAllRole();

        assertEquals(roleDTOList, result);
        Mockito.verify(roleRepository).findAll();
        Mockito.verify(roleDTOConverter).convert(roleList);
    }


    @Test
    void findById() {
    }

    @Test
    void testFindRoleById_whenRoleIdExists_shouldReturnRole() {

    }
}