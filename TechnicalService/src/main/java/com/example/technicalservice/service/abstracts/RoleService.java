package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.role.requests.CreateRoleReq;
import com.example.technicalservice.dto.role.requests.UpdateRoleReq;
import com.example.technicalservice.dto.role.responses.GetAllRoles;
import com.example.technicalservice.dto.role.responses.GetRoles;

import java.util.List;

public interface RoleService {

    DataResult<List<GetAllRoles>> getAllRoles();
    DataResult<GetRoles> getRole(long id);
    Result deleteRole(long id);
    Result createRole(CreateRoleReq createRoleReq);
    Result updateRole(long id, UpdateRoleReq updateRoleReq);
}
