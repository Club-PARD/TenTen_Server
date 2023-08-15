package com.tenten.hackathon.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
    private String userPasswordCheck;
    @ColumnDefault("0.0")
    private double userTotalWeight;

}
