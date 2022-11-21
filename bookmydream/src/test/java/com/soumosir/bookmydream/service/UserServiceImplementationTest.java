package com.soumosir.bookmydream.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.soumosir.bookmydream.cache.ForgotPasswordService;
import com.soumosir.bookmydream.cache.LoginAttemptService;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Role;
import com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser;
import com.soumosir.bookmydream.repo.AppUserRepo;
import com.soumosir.bookmydream.repo.RoleRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplementationTest {
    @MockBean
    private AppUserRepo appUserRepo;

    @MockBean
    private ForgotPasswordService forgotPasswordService;

    @MockBean
    private HttpServletRequest httpServletRequest;

    @MockBean
    private LoginAttemptService loginAttemptService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepo roleRepo;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    /**
     * Method under test: {@link UserServiceImplementation#saveUser(AppUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveUser() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Password length should be greater than 8 and less than 30 :iloveyou
        //       at com.soumosir.bookmydream.model.AppUser.validate(AppUser.java:50)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.saveUser(UserServiceImplementation.java:46)
        //   In order to prevent saveUser(AppUser)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveUser(AppUser).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        this.userServiceImplementation.saveUser(appUser);
    }

    /**
     * Method under test: {@link UserServiceImplementation#saveUser(AppUser)}
     */
    @Test
    void testSaveUser2() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.appUserRepo.save((AppUser) any())).thenReturn(appUser);
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getPassword()).thenReturn("iloveyou");
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        doNothing().when(appUser1).validate();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        assertSame(appUser, this.userServiceImplementation.saveUser(appUser1));
        verify(this.roleRepo).findByName((String) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.appUserRepo).save((AppUser) any());
        verify(appUser1).getPassword();
        verify(appUser1).getUsername();
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1, atLeast(1)).setPassword((String) any());
        verify(appUser1, atLeast(1)).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(appUser1).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#updateUser(String, AppUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Password length should be greater than 8 and less than 30 :iloveyou
        //       at com.soumosir.bookmydream.model.AppUser.validate(AppUser.java:50)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.updateUser(UserServiceImplementation.java:59)
        //   In order to prevent updateUser(String, AppUser)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateUser(String, AppUser).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        this.userServiceImplementation.updateUser("janedoe", appUser);
    }

    /**
     * Method under test: {@link UserServiceImplementation#updateUser(String, AppUser)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser1.getName()).thenReturn("Name");
        when(appUser1.getPassword()).thenReturn("iloveyou");
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        doNothing().when(appUser1).validate();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        AppUser actualUpdateUserResult = this.userServiceImplementation.updateUser("janedoe", appUser1);
        assertSame(appUser, actualUpdateUserResult);
        assertEquals("jane.doe@example.org", actualUpdateUserResult.getEmail());
        assertEquals("secret", actualUpdateUserResult.getPassword());
        assertEquals("Name", actualUpdateUserResult.getName());
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser1).getEmail();
        verify(appUser1).getName();
        verify(appUser1).getPassword();
        verify(appUser1).getUsername();
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(appUser1).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#updateUser(String, AppUser)}
     */
    @Test
    void testUpdateUser3() throws Exception {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        AppUser appUser = mock(AppUser.class);
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser1.getName()).thenReturn("Name");
        when(appUser1.getPassword()).thenReturn("iloveyou");
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        doNothing().when(appUser1).validate();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        this.userServiceImplementation.updateUser("janedoe", appUser1);
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser, atLeast(1)).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser, atLeast(1)).setName((String) any());
        verify(appUser, atLeast(1)).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(appUser1).getEmail();
        verify(appUser1).getName();
        verify(appUser1).getPassword();
        verify(appUser1).getUsername();
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(appUser1).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#updateUser(String, AppUser)}
     */
    @Test
    void testUpdateUser4() throws Exception {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser1.getName()).thenReturn("Name");
        when(appUser1.getPassword()).thenReturn("iloveyou");
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        doNothing().when(appUser1).validate();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.updateUser("janedoe", appUser1));
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(appUser1).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#saveRole(Role)}
     */
    @Test
    void testSaveRole() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleRepo.save((Role) any())).thenReturn(role);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");
        assertSame(role, this.userServiceImplementation.saveRole(role1));
        verify(this.roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#saveRole(Role)}
     */
    @Test
    void testSaveRole2() {
        when(this.roleRepo.save((Role) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.saveRole(role));
        verify(this.roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleRepo.findByName((String) any())).thenReturn(role);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        this.userServiceImplementation.addRoleToUser("janedoe", "Role Name");
        verify(this.roleRepo).findByName((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser2() {
        when(this.roleRepo.findByName((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.addRoleToUser("janedoe", "Role Name"));
        verify(this.roleRepo).findByName((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser3() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        AppUser appUser = mock(AppUser.class);
        when(appUser.getRoles()).thenReturn(new ArrayList<>());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        this.userServiceImplementation.addRoleToUser("janedoe", "Role Name");
        verify(this.roleRepo).findByName((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser).getRoles();
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((Collection<Role>) any());
        verify(appUser).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser4() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        when(appUser.getRoles()).thenReturn(new ArrayList<>());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.addRoleToUser("janedoe", "Role Name"));
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUser(String)}
     */
    @Test
    void testGetUser() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(appUser, this.userServiceImplementation.getUser("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUser(String)}
     */
    @Test
    void testGetUser2() {
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.getUser("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUser(String)}
     */
    @Test
    void testGetUser3() {
        when(this.appUserRepo.findByUsername((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.getUser("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        assertSame(appUser, this.userServiceImplementation.getUserByEmail("jane.doe@example.org"));
        verify(this.appUserRepo).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() {
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.getUserByEmail("jane.doe@example.org"));
        verify(this.appUserRepo).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail3() {
        when(this.appUserRepo.findByEmail((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.getUserByEmail("jane.doe@example.org"));
        verify(this.appUserRepo).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUsers()}
     */
    @Test
    void testGetUsers() {
        ArrayList<AppUser> appUserList = new ArrayList<>();
        when(this.appUserRepo.findAll()).thenReturn(appUserList);
        List<AppUser> actualUsers = this.userServiceImplementation.getUsers();
        assertSame(appUserList, actualUsers);
        assertTrue(actualUsers.isEmpty());
        verify(this.appUserRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImplementation#getUsers()}
     */
    @Test
    void testGetUsers2() {
        when(this.appUserRepo.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.getUsers());
        verify(this.appUserRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImplementation#deleteUser(String)}
     */
    @Test
    void testDeleteUser() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        doNothing().when(this.appUserRepo).deleteById((Long) any());
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        this.userServiceImplementation.deleteUser("janedoe");
        verify(this.appUserRepo).findByUsername((String) any());
        verify(this.appUserRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#deleteUser(String)}
     */
    @Test
    void testDeleteUser2() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        doThrow(new ResourceNotFoundException("An error occurred")).when(this.appUserRepo).deleteById((Long) any());
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.deleteUser("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
        verify(this.appUserRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#deleteUser(String)}
     */
    @Test
    void testDeleteUser3() {
        AppUser appUser = mock(AppUser.class);
        when(appUser.getId()).thenReturn(123L);
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        doNothing().when(this.appUserRepo).deleteById((Long) any());
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        this.userServiceImplementation.deleteUser("janedoe");
        verify(this.appUserRepo).findByUsername((String) any());
        verify(this.appUserRepo).deleteById((Long) any());
        verify(appUser).getId();
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#deleteUser(String)}
     */
    @Test
    void testDeleteUser4() {
        doNothing().when(this.appUserRepo).deleteById((Long) any());
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        when(appUser.getId()).thenReturn(123L);
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.deleteUser("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Password length should be greater than 8 and less than 30 :iloveyou
        //       at com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser.validate(ForgetPasswordAppUser.java:39)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.resetPassword(UserServiceImplementation.java:120)
        //   In order to prevent resetPassword(ForgetPasswordAppUser)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   resetPassword(ForgetPasswordAppUser).
        //   See https://diff.blue/R013 to resolve this issue.

        this.userServiceImplementation.resetPassword(new ForgetPasswordAppUser("jane.doe@example.org", "iloveyou", "Code"));
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Password length should be greater than 8 and less than 30 :^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$
        //       at com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser.validate(ForgetPasswordAppUser.java:39)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.resetPassword(UserServiceImplementation.java:120)
        //   In order to prevent resetPassword(ForgetPasswordAppUser)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   resetPassword(ForgetPasswordAppUser).
        //   See https://diff.blue/R013 to resolve this issue.

        this.userServiceImplementation.resetPassword(new ForgetPasswordAppUser("jane.doe@example.org",
                "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", "Code"));
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser.validate(ForgetPasswordAppUser.java:37)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.resetPassword(UserServiceImplementation.java:120)
        //   In order to prevent resetPassword(ForgetPasswordAppUser)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   resetPassword(ForgetPasswordAppUser).
        //   See https://diff.blue/R013 to resolve this issue.

        this.userServiceImplementation.resetPassword(new ForgetPasswordAppUser());
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword4() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(true);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        this.userServiceImplementation.resetPassword(forgetPasswordAppUser);
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.forgotPasswordService).verify((String) any(), (String) any());
        verify(this.appUserRepo).findByEmail((String) any());
        verify(forgetPasswordAppUser).getCode();
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).getPassword();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword5() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(false);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.resetPassword(forgetPasswordAppUser));
        verify(this.forgotPasswordService).verify((String) any(), (String) any());
        verify(this.appUserRepo).findByEmail((String) any());
        verify(forgetPasswordAppUser).getCode();
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword6() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(true);
        AppUser appUser = mock(AppUser.class);
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        this.userServiceImplementation.resetPassword(forgetPasswordAppUser);
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.forgotPasswordService).verify((String) any(), (String) any());
        verify(this.appUserRepo).findByEmail((String) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser, atLeast(1)).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(forgetPasswordAppUser).getCode();
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).getPassword();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword7() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(false);
        AppUser appUser = mock(AppUser.class);
        when(appUser.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.resetPassword(forgetPasswordAppUser));
        verify(this.forgotPasswordService).verify((String) any(), (String) any());
        verify(this.appUserRepo).findByEmail((String) any());
        verify(appUser).getUsername();
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(forgetPasswordAppUser).getCode();
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword8() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(false);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        when(appUser.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.resetPassword(forgetPasswordAppUser));
        verify(this.appUserRepo).findByEmail((String) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#resetPassword(ForgetPasswordAppUser)}
     */
    @Test
    void testResetPassword9() throws ValidationException {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.forgotPasswordService.verify((String) any(), (String) any())).thenReturn(false);
        AppUser appUser = mock(AppUser.class);
        when(appUser.getUsername()).thenThrow(new ResourceNotFoundException("An error occurred"));
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setName((String) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByEmail((String) any())).thenReturn(ofResult);
        ForgetPasswordAppUser forgetPasswordAppUser = mock(ForgetPasswordAppUser.class);
        when(forgetPasswordAppUser.getCode()).thenReturn("Code");
        when(forgetPasswordAppUser.getEmail()).thenReturn("jane.doe@example.org");
        when(forgetPasswordAppUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(forgetPasswordAppUser).validate();
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImplementation.resetPassword(forgetPasswordAppUser));
        verify(this.forgotPasswordService).verify((String) any(), (String) any());
        verify(this.appUserRepo).findByEmail((String) any());
        verify(appUser).getUsername();
        verify(appUser).setEmail((String) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setName((String) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setRoles((java.util.Collection<Role>) any());
        verify(appUser).setUsername((String) any());
        verify(forgetPasswordAppUser).getCode();
        verify(forgetPasswordAppUser).getEmail();
        verify(forgetPasswordAppUser).validate();
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> this.userServiceImplementation.loadUserByUsername("janedoe"));
        verify(this.loginAttemptService).isBlocked((String) any());
        verify(this.httpServletRequest).getHeader((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenThrow(new UsernameNotFoundException("X-Forwarded-For"));
        when(this.httpServletRequest.getHeader((String) any())).thenThrow(new UsernameNotFoundException("X-Forwarded-For"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImplementation.loadUserByUsername("janedoe"));
        verify(this.httpServletRequest).getHeader((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(false);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImplementation.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.loginAttemptService).isBlocked((String) any());
        verify(this.httpServletRequest).getHeader((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.getClientIP(UserServiceImplementation.java:160)
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.loadUserByUsername(UserServiceImplementation.java:140)
        //   In order to prevent loadUserByUsername(String)
        //   from throwing ArrayIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   loadUserByUsername(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn(",");

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        this.userServiceImplementation.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn(null);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> this.userServiceImplementation.loadUserByUsername("janedoe"));
        verify(this.loginAttemptService).isBlocked((String) any());
        verify(this.httpServletRequest).getRemoteAddr();
        verify(this.httpServletRequest).getHeader((String) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername6() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.soumosir.bookmydream.service.UserServiceImplementation.loadUserByUsername(UserServiceImplementation.java:138)
        //   In order to prevent loadUserByUsername(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   loadUserByUsername(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(null);
        this.userServiceImplementation.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link UserServiceImplementation#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername7() throws UsernameNotFoundException {
        when(this.loginAttemptService.isBlocked((String) any())).thenReturn(true);
        when(this.httpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(this.httpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImplementation.loadUserByUsername("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
    }
}

