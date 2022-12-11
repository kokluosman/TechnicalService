package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.RoleRepository;
import com.example.technicalservice.dto.role.requests.CreateRoleReq;
import com.example.technicalservice.dto.role.requests.UpdateRoleReq;
import com.example.technicalservice.dto.role.responses.GetAllRoles;
import com.example.technicalservice.dto.role.responses.GetRoles;
import com.example.technicalservice.model.Role;
import com.example.technicalservice.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {

    private final RoleRepository repository;

    @Override
    public DataResult<List<GetAllRoles>> getAllRoles() {
        List<Role> all = this.repository.findAll();
        List<GetAllRoles> allRoles = new ArrayList<>();
        for (Role role : all) {
            GetAllRoles roles = new GetAllRoles();
            roles.setName(role.getName());
            roles.setId(role.getId());

            allRoles.add(roles);
        }
        return new SuccessDataResult<>(allRoles,"Roles are Successfully Listed!");
    }

    @Override
    public DataResult<GetRoles> getRole(long id) {
        isNotExist(id);
        Role role = this.repository.findById(id).get();
        GetRoles roles = new GetRoles();
        roles.setName(role.getName());
        return new SuccessDataResult<>(roles,"Role Successfully Fetched By Id");
    }

    @Override
    public Result deleteRole(long id) {
        isNotExist(id);
        this.repository.deleteById(id);
        return new SuccessResult("Role Successfully Deleted!");
    }

    @Override
    public Result createRole(CreateRoleReq createRoleReq) {
        Role role = new Role();
        role.setName(createRoleReq.getName());
        return new SuccessResult("Successfully Created!!");
    }

    @Override
    public Result updateRole(long id, UpdateRoleReq updateRoleReq) {
        isNotExist(id);
        Role role = this.repository.findById(id).get();
        role.setName(updateRoleReq.getName());
        this.repository.save(role);
        return new SuccessResult("Role Successfully Updated!");
    }
    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
