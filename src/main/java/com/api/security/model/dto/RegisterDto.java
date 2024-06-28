package com.api.security.model.dto;

import com.api.security.model.domain.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
    @NotBlank(message = "The username cannot be blank")
    private String username;

    @NotBlank(message = "The password cannot be blank")
    private String password;

    @NotBlank(message = "The email cannot be blank")
    @Email(message = "The email is invalid")
    private String email;

    private UserRoleEnum role;

    public @NotBlank(message = "The username cannot be blank") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "The username cannot be blank") String username) {
        this.username = username;
    }

    public @NotBlank(message = "The password cannot be blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "The password cannot be blank") String password) {
        this.password = password;
    }

    public @NotBlank(message = "The email cannot be blank") @Email(message = "The email is invalid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "The email cannot be blank") @Email(message = "The email is invalid") String email) {
        this.email = email;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
