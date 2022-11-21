package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.model.Role;
import com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser) throws Exception;
    AppUser updateUser(String username,AppUser appUser) throws Exception;
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    AppUser getUserByEmail(String email);
    List<AppUser> getUsers();
    void deleteUser(String username);
    void resetPassword(ForgetPasswordAppUser forgetPasswordAppUser);
}
