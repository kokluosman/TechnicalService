package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.user.requests.CreateUserReq;
import com.example.technicalservice.dto.user.requests.UpdateUserReq;
import com.example.technicalservice.dto.user.responses.UserGetAllResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;

import java.util.List;

public interface UserService {

    DataResult<List<UserGetAllResponse>> getAllUser();
    DataResult<UserGetResponse> getById(long id);
    DataResult<UpdateUserReq> updateUser(long id,UpdateUserReq updateUserReq);
    DataResult<CreateUserReq> createUser(CreateUserReq  createUserReq);
    Result deleteUser(long id);
    DataResult<UserGetResponse> getUserByEmail(String email);
    DataResult<List<UserGetResponse>> getUserByName(String name);
    DataResult<List<UserGetResponse>> getUserContains(String email);

}
