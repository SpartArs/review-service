package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    @Min(4)
    private String password;

//    @AssertTrue
//    public boolean isValid() {
//        return false;
//    }
}
