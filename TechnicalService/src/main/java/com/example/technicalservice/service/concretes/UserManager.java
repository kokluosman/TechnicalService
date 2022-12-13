package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.UserRepository;
import com.example.technicalservice.dto.user.requests.CreateUserReq;
import com.example.technicalservice.dto.user.requests.UpdateUserReq;
import com.example.technicalservice.dto.user.responses.UserGetAllResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;
import com.example.technicalservice.model.User;
import com.example.technicalservice.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    public DataResult<List<UserGetAllResponse>> getAllUser() {
        List<User> allUser = this.userRepository.findAll();
        List<UserGetAllResponse> userGetAllResponses = new ArrayList<>();
        for (User user : allUser) {
            UserGetAllResponse userGetAllResponse = new UserGetAllResponse();
            userGetAllResponse.setEmail(user.getEmail());
            userGetAllResponse.setId(user.getId());
            userGetAllResponse.setName(user.getName());
            userGetAllResponse.setPassword(user.getPassword());
            userGetAllResponse.setRoles(user.getRoles());
            userGetAllResponses.add(userGetAllResponse);
        }
        return new SuccessDataResult<>(userGetAllResponses,"Data Successfully Listed!");
    }

    @Override
    public DataResult<UserGetResponse> getById(long id) {
        isNotExistId(id);
        Optional<User> userById = this.userRepository.findById(id);
        UserGetResponse userGetResponse = new UserGetResponse();
        userById.stream()
                .map(user -> {
                    userGetResponse.setEmail(user.getEmail());
                    userGetResponse.setName(user.getName());
                    userGetResponse.setRoles(user.getRoles());
                    return userGetResponse;
                });
        log.info("GetById services {} running",userGetResponse.getName());
        return new SuccessDataResult<>(userGetResponse,"The User Successfully getting!");
    }

    @Override
    public DataResult<UpdateUserReq> updateUser(long id, UpdateUserReq updateUserReq) {
        isNotExistId(id);
        User user = this.userRepository.findById(id).get();
        user.setEmail(updateUserReq.getEmail());
        user.setName(updateUserReq.getName());
        user.setRoles(updateUserReq.getRoles());
        user.setPassword(updateUserReq.getPassword());
        this.userRepository.save(user);
        return new SuccessDataResult<>(updateUserReq,"User Successfully updated!!");
    }

    @Override
    public DataResult<CreateUserReq> createUser(CreateUserReq createUserReq) {
        User user = new User();
        user.setPassword(createUserReq.getPassword());
        user.setName(createUserReq.getName());
        user.setEmail(createUserReq.getEmail());
        user.setRoles(createUserReq.getRoles());
        this.userRepository.save(user);
        return new SuccessDataResult<>(createUserReq,"User Successfully added!!");
    }

    @Override
    public Result deleteUser(long id) {
        isNotExistId(id);
        this.userRepository.deleteById(id);
        return new SuccessResult("User Successfully deleted!!");
    }

    @Override
    public DataResult<UserGetResponse> getUserByEmail(String email) {
        User byEmail = this.userRepository.findByEmail(email);
        UserGetResponse userGetResponse = new UserGetResponse();
        userGetResponse.setRoles(byEmail.getRoles());
        userGetResponse.setName(byEmail.getName());
        userGetResponse.setEmail(byEmail.getEmail());
        return new SuccessDataResult<>(userGetResponse,"User getting ByEmail Successfully!!");
    }

    @Override
    public DataResult<List<UserGetResponse>> getUserByName(String name) {
        List<User> users = this.userRepository.findByNameOrderByName(name);
        List<UserGetResponse> userGetResponses = new ArrayList<>();
        for (User user : users) {
            UserGetResponse userGetResponse = new UserGetResponse();
            userGetResponse.setEmail(user.getEmail());
            userGetResponse.setName(user.getName());
            userGetResponse.setRoles(user.getRoles());
            userGetResponses.add(userGetResponse);
        }
        return new SuccessDataResult<>(userGetResponses,"Data Successfully Listed by name");
    }

    @Override
    public DataResult<List<UserGetResponse>> getUserContains(String email) {
        List<User> users = this.userRepository.findByEmailContainingIgnoreCase(email);
        List<UserGetResponse> userGetResponses = new ArrayList<>();
        for (User user : users) {
            UserGetResponse userGetResponse = new UserGetResponse();
            userGetResponse.setEmail(user.getEmail());
            userGetResponse.setName(user.getName());
            userGetResponse.setRoles(user.getRoles());
            userGetResponses.add(userGetResponse);
        }
        return new SuccessDataResult<>(userGetResponses,"Data Successfully Listed by name");
    }

    void isExist(long id){
        if (this.userRepository.existsById(id)){
            throw new BusinessException("This id is already exist");
        }
    }

    void isNotExistId(long id){
        if (!this.userRepository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }

}
