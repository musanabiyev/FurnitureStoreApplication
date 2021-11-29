package com.company.controller;

import com.company.dto.RoleDTO;
import com.company.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/add")
    public ResponseEntity<RoleDTO> createRole(@RequestParam("name") String name) {
        return ResponseEntity.ok(roleService.createRole(name));
    }

    @PutMapping("/update")
    public ResponseEntity<RoleDTO> updateRole(
            @RequestParam("id") int id, @RequestParam("name") String name) {
        return ResponseEntity.ok(roleService.updateRole(id, name));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<RoleDTO>> getAllRole() {
        return ResponseEntity.ok(roleService.findAllRole());
    }

    @GetMapping("/get")
    public ResponseEntity<RoleDTO> getByIdRole(@PathVariable("id") int id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }


}
