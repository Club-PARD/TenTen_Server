package com.tenten.hackathon.entity;

import com.tenten.hackathon.dto.SignInDto;
import com.tenten.hackathon.dto.SignUpDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "User")
@Table(name = "User")
@Data
public class UserEntity {
    @Id
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
    @ColumnDefault("0.0")
    private double userTotalWeight;
    public UserEntity(SignUpDto dto){
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
    }
}
