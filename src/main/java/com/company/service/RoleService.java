package com.company.service;


import com.company.dto.RoleDTO;
import com.company.dto.converter.RoleDTOConverter;
import com.company.exception.RoleNotFoundException;
import com.company.model.Role;
import com.company.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleDTOConverter roleDTOConverter;

    public RoleService(RoleRepository roleRepository, RoleDTOConverter roleDTOConverter) {
        this.roleRepository = roleRepository;
        this.roleDTOConverter = roleDTOConverter;
    }

    public RoleDTO createRole(String name) {
        Role role = new Role(name);
        return roleDTOConverter.convert(roleRepository.save(role));
    }

    public RoleDTO updateRole(int id, String name) {
        Role finRoleId = findRoleById(id);
        Role role = new Role();
        role.setId(finRoleId.getId());
        role.setName(name);
        return roleDTOConverter.convert(roleRepository.save(role));
    }

    public void deleteRole(int id) {
        Role role = findRoleById(id);
        roleRepository.delete(role);
    }

    public List<RoleDTO> findAllRole() {
        return roleDTOConverter.convert(roleRepository.findAll());
    }

    public RoleDTO findById(int id) {
        return roleDTOConverter.convert(findRoleById(id));
    }

    public RoleDTO findByName(String name) {
        return roleDTOConverter.convert(roleRepository.findByName(name));
    }

    protected Role findRoleById(int id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException("Role with id " + id + " could not found"));

    }

    protected Role findRoleByName(String name) {
        return roleRepository.findByName(name);

    }
}
