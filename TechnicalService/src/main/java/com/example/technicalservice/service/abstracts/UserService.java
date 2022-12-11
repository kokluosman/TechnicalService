package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.user.requests.CreateUserReq;
import com.example.technicalservice.dto.user.requests.UpdateUserReq;
import com.example.technicalservice.dto.user.responses.GetAllUser;
import com.example.technicalservice.dto.user.responses.GetUser;

import java.util.List;

public interface UserService {

    DataResult<List<GetAllUser>> getAllUser();
    DataResult<GetUser> getById(long id);
    DataResult<UpdateUserReq> updateUser(long id,UpdateUserReq updateUserReq);
    DataResult<CreateUserReq> createUser(CreateUserReq  createUserReq);
    Result deleteUser(long id);
    DataResult<GetUser> getUserByEmail(String email);
    DataResult<List<GetUser>> getUserByName(String name);
    DataResult<List<GetUser>> getUserContains(String email);

}
