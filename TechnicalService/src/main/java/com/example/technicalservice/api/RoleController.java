package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.role.requests.CreateRoleReq;
import com.example.technicalservice.dto.role.requests.UpdateRoleReq;
import com.example.technicalservice.dto.role.responses.GetAllRoles;
import com.example.technicalservice.dto.role.responses.GetRoles;
import com.example.technicalservice.service.abstracts.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleService service;

    @GetMapping(path = "/getall")
    DataResult<List<GetAllRoles>> getAllRoles(){
        return this.service.getAllRoles();
    }
    @GetMapping(path = "getById/{id}")
    DataResult<GetRoles> getRole(@PathVariable(name = "id") long id){
        return this.service.getRole(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result deleteRole(@PathVariable(name = "id") long id){
        return this.service.deleteRole(id);
    }
    @PostMapping(path = "/create")
    Result createRole(@Valid @RequestBody CreateRoleReq createRoleReq){
        return this.service.createRole(createRoleReq);
    }
    @PutMapping(path = "/update/{id}")
    Result updateRole(@PathVariable(name = "id") long id,@Valid@RequestBody UpdateRoleReq updateRoleReq){
        return this.service.updateRole(id, updateRoleReq);
    }
}
