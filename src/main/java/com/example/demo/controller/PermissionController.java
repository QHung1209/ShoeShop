package com.example.demo.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import com.example.demo.domain.entity.Permission;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import com.example.demo.util.error.IdInvalidException;

@RestController
public class PermissionController {

    final private PermissionService permissionService;
    final private PermissionRepository permissionRepository;

    public PermissionController(PermissionService permissionService, PermissionRepository permissionRepository) {
        this.permissionService = permissionService;
        this.permissionRepository = permissionRepository;
    }

    @PostMapping("permissions")
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody Permission permission)
            throws IdInvalidException {
        if (this.permissionRepository.existsByApiPathAndMethodAndModule(permission.getApiPath(), permission.getMethod(),
                permission.getModule())) {
            throw new IdInvalidException("permission da ton tai");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.permissionService.handleCreatePermission(permission));
    }

    @GetMapping("permissions/{id}")
    public ResponseEntity<Permission> getPermission(@PathVariable("id") long id) throws IdInvalidException {
        Permission current = this.permissionService.handleGetPermissionById(id);
        if (current == null) {
            throw new IdInvalidException("Permission Id khong ton tai");
        }
        return ResponseEntity.ok(current);
    }

    @GetMapping("permissions")
    public ResponseEntity<ResultPaginationDTO> getAllPermission(@Filter Specification<Permission> specification,
            Pageable pageable) {
        return ResponseEntity.ok(this.permissionService.handleGetAllPermission(specification, pageable));
    }

    @PutMapping("permissions")
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission) throws IdInvalidException {
        if (this.permissionService.handleGetPermissionById(permission.getId()) == null) {
            throw new IdInvalidException("Permission id khong ton tai");
        }

        if (this.permissionService.isPermissionExists(permission) == true) {
            throw new IdInvalidException("Permission da ton tai");
        }

        return ResponseEntity.ok(this.permissionService.handleUpdatePermission(permission));
    }

    @DeleteMapping("permissions")
    public ResponseEntity<Void> deletePermission(@PathVariable("id") long id) throws IdInvalidException {
        if (this.permissionService.handleGetPermissionById(id) == null) {
            throw new IdInvalidException("Permission id khong ton tai");
        }

        this.permissionService.handleDeletePermission(id);
        return ResponseEntity.ok(null);
    }

}
