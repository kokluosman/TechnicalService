package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.user.requests.CreateUserReq;
import com.example.technicalservice.dto.user.requests.UpdateUserReq;
import com.example.technicalservice.dto.user.responses.UserGetAllResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;
import com.example.technicalservice.service.abstracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UsersController {

    private final UserService userService;

    @GetMapping(path = "/getall")
    DataResult<List<UserGetAllResponse>> getAllUser(){
        return this.userService.getAllUser();
    }
    @GetMapping(path = "/getuser/{id}")
    DataResult<UserGetResponse> getById(@PathVariable(name = "id") long id){
        return this.userService.getById(id);
    }
    @PutMapping(path = "/update/{id}")
    DataResult<UpdateUserReq> updateUser(@PathVariable(name = "id") long id,
                                         @Valid @RequestBody UpdateUserReq updateUserReq){
        return this.userService.updateUser(id, updateUserReq);
    }
    @PostMapping(path = "/create")
    DataResult<CreateUserReq> createUser(@Valid @RequestBody CreateUserReq  createUserReq){
        return this.userService.createUser(createUserReq);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result deleteUser(@PathVariable(name = "id") long id){
        return this.userService.deleteUser(id);
    }
    @GetMapping(path = "/getByEmail")
    DataResult<UserGetResponse> getUserByEmail(@RequestBody String email){
        return this.userService.getUserByEmail(email);
    }
    @GetMapping(path = "/getByUsername")
    DataResult<List<UserGetResponse>> getUserByName(@RequestBody String name){
        return this.userService.getUserByName(name);
    }
    @GetMapping(path = "/getContains")
    DataResult<List<UserGetResponse>> getUserContains(@RequestBody String email){
        return this.userService.getUserContains(email);
    }

}
