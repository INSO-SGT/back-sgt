package com.plenamente.sgt.domain.dto.UserDto;

import com.plenamente.sgt.domain.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MyProfile(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 100) String paternalSurname,
        @Length(max = 100) String maternalSurname,
        @Length(max = 100) String address,
        @NotBlank @Length(max = 9) String phone,
        @Length(max = 9) String phoneBackup,
        @NotBlank @Email String email,
        Rol role // Opcional para mostrar el rol
) {}