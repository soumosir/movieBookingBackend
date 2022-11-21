package com.soumosir.bookmydream.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.cache.ForgotPasswordService;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Role;
import com.soumosir.bookmydream.model.helper.ForgetPasswordAppUser;
import com.soumosir.bookmydream.service.EmailService;
import com.soumosir.bookmydream.service.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private EmailService emailService;

    @MockBean
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getUsers()}
     */
    @Test
    void testGetUsers() throws Exception {
        when(this.userService.getUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getUsers()}
     */
    @Test
    void testGetUsers2() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("?");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        ArrayList<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        when(this.userService.getUsers()).thenReturn(appUserList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}]"));
    }

    /**
     * Method under test: {@link UserController#getUsers()}
     */
    @Test
    void testGetUsers3() throws Exception {
        when(this.userService.getUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/users");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getUser(String)}
     */
    @Test
    void testGetUser() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.getUser((String) any())).thenReturn(appUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"));
    }

    /**
     * Method under test: {@link UserController#getUser(String)}
     */
    @Test
    void testGetUser2() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("?");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(roleList);
        appUser.setUsername("janedoe");
        when(this.userService.getUser((String) any())).thenReturn(appUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[\"?\"]}"));
    }

    /**
     * Method under test: {@link UserController#getUser(String)}
     */
    @Test
    void testGetUser3() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.getUser((String) any())).thenReturn(appUser);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/user/{username}", "janedoe");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(String, AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testUpdateUser() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.updateUser((String) any(), (AppUser) any())).thenReturn(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/{username}", "janedoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/janedoe"));
    }

    /**
     * Method under test: {@link UserController#updateUser(String, AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("?");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(roleList);
        appUser.setUsername("janedoe");
        when(this.userService.updateUser((String) any(), (AppUser) any())).thenReturn(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/{username}", "janedoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[\"?\"]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/janedoe"));
    }

    /**
     * Method under test: {@link UserController#updateUser(String, AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testUpdateUser3() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.updateUser((String) any(), (AppUser) any())).thenReturn(appUser);

        Role role = new Role();
        role.setId(123L);
        role.setName("?");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(roleList);
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/{username}", "janedoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/janedoe"));
    }

    /**
     * Method under test: {@link UserController#updateUser(String, AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testUpdateUser4() throws Exception {
        when(this.userService.updateUser((String) any(), (AppUser) any())).thenThrow(new Exception("An error occurred"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/{username}", "janedoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":\"An error occurred\"}"));
    }

    /**
     * Method under test: {@link UserController#saveUser(AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSaveUser2() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.saveUser((AppUser) any())).thenReturn(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/register"));
    }

    /**
     * Method under test: {@link UserController#saveUser(AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSaveUser3() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("?");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(roleList);
        appUser.setUsername("janedoe");
        when(this.userService.saveUser((AppUser) any())).thenReturn(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[\"?\"]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/register"));
    }

    /**
     * Method under test: {@link UserController#saveUser(AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSaveUser4() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.saveUser((AppUser) any())).thenReturn(appUser);

        Role role = new Role();
        role.setId(123L);
        role.setName("?");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(roleList);
        appUser1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"roles\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/user/register"));
    }

    /**
     * Method under test: {@link UserController#saveUser(AppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSaveUser5() throws Exception {
        when(this.userService.saveUser((AppUser) any())).thenThrow(new Exception("An error occurred"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error_message\":\"An error occurred ::  Email or Username already exists!\"}"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(String, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.userService).deleteUser((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#deleteUser(String, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.userService).deleteUser((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/user/{username}", "janedoe");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#deleteUser(String, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testDeleteUser3() throws Exception {
        doThrow(new RuntimeException("An error occurred")).when(this.userService).deleteUser((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/{username}", "janedoe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error_message\":\"An error occurred ::  Username doesnt exists!\"}"));
    }

    /**
     * Method under test: {@link UserController#refreshToken(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#resetUserPassword(ForgetPasswordAppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testResetUserPassword() throws Exception {
        doNothing().when(this.userService).resetPassword((ForgetPasswordAppUser) any());

        ForgetPasswordAppUser forgetPasswordAppUser = new ForgetPasswordAppUser();
        forgetPasswordAppUser.setCode("Code");
        forgetPasswordAppUser.setEmail("jane.doe@example.org");
        forgetPasswordAppUser.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(forgetPasswordAppUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/resetpassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Password has been reset!"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/user/resetpassword"));
    }

    /**
     * Method under test: {@link UserController#resetUserPassword(ForgetPasswordAppUser, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testResetUserPassword2() throws Exception {
        doThrow(new RuntimeException("An error occurred")).when(this.userService)
                .resetPassword((ForgetPasswordAppUser) any());

        ForgetPasswordAppUser forgetPasswordAppUser = new ForgetPasswordAppUser();
        forgetPasswordAppUser.setCode("Code");
        forgetPasswordAppUser.setEmail("jane.doe@example.org");
        forgetPasswordAppUser.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(forgetPasswordAppUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/resetpassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":\"An error occurred\"}"));
    }

    /**
     * Method under test: {@link UserController#resetUserPasswordCodeRequest(String)}
     */
    @Test
    void testResetUserPasswordCodeRequest() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.getUserByEmail((String) any())).thenReturn(appUser);
        when(this.forgotPasswordService.setRandomCode((String) any())).thenReturn("Random Code");
        doNothing().when(this.emailService).sendEmail((String) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/resetpassword/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Email sent to jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserController#resetUserPasswordCodeRequest(String)}
     */
    @Test
    void testResetUserPasswordCodeRequest2() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        when(this.userService.getUserByEmail((String) any())).thenReturn(appUser);
        when(this.forgotPasswordService.setRandomCode((String) any())).thenReturn("Random Code");
        doNothing().when(this.emailService).sendEmail((String) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/resetpassword/{email}",
                "jane.doe@example.org");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Email sent to jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserController#saveUser(RoleToAppUserForm)}
     */
    @Test
    void testSaveUser() throws Exception {
        doNothing().when(this.userService).addRoleToUser((String) any(), (String) any());

        RoleToAppUserForm roleToAppUserForm = new RoleToAppUserForm();
        roleToAppUserForm.setRoleName("Role Name");
        roleToAppUserForm.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(roleToAppUserForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/role/addtouser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#saveUser(Role)}
     */
    @Test
    void testSaveUser6() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.userService.saveRole((Role) any())).thenReturn(role);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(role1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/role/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\"}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/role/save"));
    }
}

