package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.UserRepository;
import com.example.technicalservice.dto.user.requests.CreateUserReq;
import com.example.technicalservice.dto.user.requests.UpdateUserReq;
import com.example.technicalservice.dto.user.responses.GetAllUser;
import com.example.technicalservice.dto.user.responses.GetUser;
import com.example.technicalservice.model.User;
import com.example.technicalservice.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    public DataResult<List<GetAllUser>> getAllUser() {
        List<User> allUser = this.userRepository.findAll();
        List<GetAllUser> getAllUsers = new ArrayList<>();
        for (User user : allUser) {
            GetAllUser getAllUser = new GetAllUser();
            getAllUser.setEmail(user.getEmail());
            getAllUser.setId(user.getId());
            getAllUser.setName(user.getName());
            getAllUser.setPassword(user.getPassword());
            getAllUser.setRoles(user.getRoles());
            getAllUsers.add(getAllUser);
        }
        return new SuccessDataResult<>(getAllUsers,"Data Successfully Listed!");
    }

    @Override
    public DataResult<GetUser> getById(long id) {
        isNotExist(id);
        Optional<User> userById = this.userRepository.findById(id);
        GetUser getUser = new GetUser();
        userById.stream()
                .map(user -> {
                    getUser.setEmail(user.getEmail());
                    getUser.setName(user.getName());
                    getUser.setRoles(user.getRoles());
                    return getUser;
                });
        return new SuccessDataResult<>(getUser,"The User Successfully getting!");
    }

    @Override
    public DataResult<UpdateUserReq> updateUser(long id, UpdateUserReq updateUserReq) {
        isNotExist(id);
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
        isNotExist(id);
        this.userRepository.deleteById(id);
        return new SuccessResult("User Successfully deleted!!");
    }

    @Override
    public DataResult<GetUser> getUserByEmail(String email) {
        User byEmail = this.userRepository.findByEmail(email);
        GetUser getUser = new GetUser();
        getUser.setRoles(byEmail.getRoles());
        getUser.setName(byEmail.getName());
        getUser.setEmail(byEmail.getEmail());
        return new SuccessDataResult<>(getUser,"User getting ByEmail Successfully!!");
    }

    @Override
    public DataResult<List<GetUser>> getUserByName(String name) {
        List<User> users = this.userRepository.findByNameOrderByName(name);
        List<GetUser> getUsers = new ArrayList<>();
        for (User user : users) {
            GetUser getUser = new GetUser();
            getUser.setEmail(user.getEmail());
            getUser.setName(user.getName());
            getUser.setRoles(user.getRoles());
            getUsers.add(getUser);
        }
        return new SuccessDataResult<>(getUsers,"Data Successfully Listed by name");
    }

    @Override
    public DataResult<List<GetUser>> getUserContains(String email) {
        List<User> users = this.userRepository.findByEmailContainingIgnoreCase(email);
        List<GetUser> getUsers = new ArrayList<>();
        for (User user : users) {
            GetUser getUser = new GetUser();
            getUser.setEmail(user.getEmail());
            getUser.setName(user.getName());
            getUser.setRoles(user.getRoles());
            getUsers.add(getUser);
        }
        return new SuccessDataResult<>(getUsers,"Data Successfully Listed by name");
    }

    void isExist(long id){
        if (this.userRepository.existsById(id)){
            throw new BusinessException("This id is already exist");
        }
    }

    void isNotExist(long id){
        if (!this.userRepository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }

}
