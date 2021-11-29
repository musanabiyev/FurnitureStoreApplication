package com.company.service;


import com.company.dto.converter.UserRoleDTOConverter;
import com.company.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleDTOConverter userRoleDTOConverter;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRoleDTOConverter userRoleDTOConverter) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleDTOConverter = userRoleDTOConverter;
    }


}
