package com.example.demo.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.turkraft.springfilter.boot.Filter;

import com.example.demo.domain.entity.Role;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.domain.response.user.ResCreateUserDTO;
import com.example.demo.domain.response.user.ResUpdateUserDTO;
import com.example.demo.domain.response.user.ResUserDTO;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;

    }

    public User handleCreateUser(User user) {
        if (user.getRole() != null) {
            Role role = this.roleService.handleGetRole(user.getRole().getId());
            user.setRole(role != null ? role : null);
        }

        return this.userRepository.save(user);
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User handleGetUser(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public ResultPaginationDTO handleGetAllUser(@Filter Specification<User> specification, Pageable pageable) {
        Page<User> pageUser = this.userRepository.findAll(specification, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());

        rs.setMeta(mt);
        List<User> listUsers = pageUser.getContent();
        rs.setResult(this.convertToListUserDTO(listUsers));
        return rs;
    }

    public User handleUpdateUser(User user) {
        User currentUser = this.handleGetUser(user.getId());
        if (currentUser != null) {
            currentUser.setName(user.getName());
            currentUser.setGender(user.getGender());
            currentUser.setAddress(user.getAddress());
            currentUser.setAge(user.getAge());

            if (user.getRole() != null) {
                Role role = this.roleService.handleGetRole(user.getRole().getId());
                currentUser.setRole(role != null ? role : null);
            }

            currentUser = this.userRepository.save(currentUser);
        }

        return currentUser;
    }

    public User handleGetUserByUserName(String username) {
        return this.userRepository.findByEmail(username);
    }

    public boolean isEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        ResCreateUserDTO res = new ResCreateUserDTO();
        ResCreateUserDTO.RoleUser rol = new ResCreateUserDTO.RoleUser();
        res.setAddress(user.getAddress());
        res.setAge(user.getAge());
        res.setCreatedAt(user.getCreatedAt());
        res.setEmail(user.getEmail());
        res.setGenderEnum(user.getGender());
        res.setId(user.getId());
        res.setName(user.getName());

        if (user.getRole() != null) {
            rol.setId(user.getRole().getId());
            rol.setName(user.getRole().getName());
            res.setRole(rol);
        }

        return res;
    }

    public ResUserDTO convertToResUserDTO(User user) {
        ResUserDTO res = new ResUserDTO();
        res.setAddress(user.getAddress());
        res.setAge(user.getAge());
        res.setCreatedAt(user.getCreatedAt());
        res.setEmail(user.getEmail());
        res.setGenderEnum(user.getGender());
        res.setId(user.getId());
        res.setName(user.getName());
        res.setCreatedBy(user.getCreatedBy());

        ResCreateUserDTO.RoleUser rol = new ResCreateUserDTO.RoleUser();
        if (user.getRole() != null) {
            rol.setId(user.getRole().getId());
            rol.setName(user.getRole().getName());
            res.setRole(rol);
        }
        return res;
    }

    public List<ResUserDTO> convertToListUserDTO(List<User> listUsers) {
        List<ResUserDTO> listUserDTOs = new ArrayList<>();
        for (User user : listUsers) {
            ResUserDTO res = convertToResUserDTO(user);
            listUserDTOs.add(res);
        }
        return listUserDTOs;
    }

    public ResUpdateUserDTO convertToResUpdateUserDTO(User user) {
        ResUpdateUserDTO res = new ResUpdateUserDTO();
        res.setAddress(user.getAddress());
        res.setAge(user.getAge());
        res.setGender(user.getGender());
        res.setName(user.getName());
        res.setId(user.getId());
        res.setUpdatedAt(user.getUpdatedAt());

        ResCreateUserDTO.RoleUser rol = new ResCreateUserDTO.RoleUser();
        if (user.getRole() != null) {
            rol.setId(user.getRole().getId());
            rol.setName(user.getRole().getName());
            res.setRole(rol);
        }

        return res;
    }

    public void updateUserToken(String token, String email) {
        User current = this.handleGetUserByUserName(email);
        if (current != null) {
            current.setRefreshToken(token);
            this.userRepository.save(current);
        }
    }

    public User getUserByRefreshTokenAndEmail(String token, String email) {
        return this.userRepository.findByRefreshTokenAndEmail(token, email);
    }
}
