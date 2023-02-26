package com.makowski.shop.web.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.dto.UserDto;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.exception.ErrorResponse;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "1.1 User", description = "Manage User enitity")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @Operation(summary = "Create Customer", description = "Creates a User from the provided payload with role CUSTOMER")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful create user with role CUSTOMER"),
        @ApiResponse(responseCode = "400", description = "Customer not created, check Request Body", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  
    })
    @PostMapping("/registerCustomer")
    public ResponseEntity<User> createCustomer(@Valid @RequestBody User user){
        userService.createCustomer(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Create Employee with e-mail verification", description = "Creates a User from the provided payload with role EMPLOYEE. Make User 'Disabled' and send Verification e-mail with 'token' to confirm e-mail address. Token expided after 20min")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful create user with role EMPLOEE. E-mail with verification token was sent"),
        @ApiResponse(responseCode = "400", description = "Customer not created, check Request Body", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  
    })
    @PostMapping("/registerEmployee")
    public ResponseEntity<User> createEmployee(@Valid @RequestBody User user){
        userService.createEmployee(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Create Admin", description = "Creates a user from the provided payload with role Admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful create user with role ADMIN"),
        @ApiResponse(responseCode = "400", description = "Customer not created, check Request Body", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  
    })
    @PostMapping("/registerAdmin")
    public ResponseEntity<User> createAdmin(@Valid @RequestBody User user){
        userService.createAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Confirm User e-mail (only for Emploee)", description = "Check if provided token is in database and is not expired. If token is valid enable user who own token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful confirm user e-mail"),
        @ApiResponse(responseCode = "400", description = "User not confirmed, Token not not valid or expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  
    })
    @GetMapping("/verificationToken/{token}")
    public ResponseEntity<String> confirmUser(@PathVariable String token){
        userService.confirmUser(token);
        return new ResponseEntity<>("Confirmed", HttpStatus.OK);
    }

    @Operation(summary = "Find User by id", description = "Checks if user with {id} exist in database. Request can make only user with role ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User with {id} is in database"),
        @ApiResponse(responseCode = "404", description = "User with {id} does not exist in database", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find User by username", description = "Checks if user with {username} exist in database. Request can make only user with role ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User with {username} is in database."),
        @ApiResponse(responseCode = "404", description = "User with {username} does not exist in database", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return  new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
    }
    
    @Operation(summary = "Get list with all Users", description = "Return list with all users from databese. Request can make only user with role ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Return list with all users from databese"),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @GetMapping("/all")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Delete User by id", description = "Remove user with {id} from database. User can remove only yourself from database. User with role ADMIN can remove every user from database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User with {id} is removed from database."),
        @ApiResponse(responseCode = "404", description = "User with {id} does not exist in database", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update User by id", description = "Edit user with {id}. User can edit only yourself. User with role ADMIN can edit every user from database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User with {id} is successful updated."),
        @ApiResponse(responseCode = "404", description = "User with {id} does not exist in database", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "User not updated, check Request Body", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @Operation(summary = "Change password by username", description = "Change password from user with {username}. User can change only his own password and need to provide his old password as well")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password changed"),
        @ApiResponse(responseCode = "404", description = "User with {username} does not exist in database", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User dont have authorization to do this request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "Passowrd not changed, check Request Body", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))  
    })
    @PutMapping("/username/{username}")
    public ResponseEntity<UserDto> changePassword(@PathVariable String username, @RequestBody UserDto userDto){
        userService.changePassword(username, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
