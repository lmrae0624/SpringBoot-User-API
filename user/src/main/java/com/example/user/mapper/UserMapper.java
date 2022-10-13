package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.dto.InputUserRequestDto;
import com.example.user.dto.UpdateUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper instance  = Mappers.getMapper(UserMapper.class);

    User inputUserRequestDtoToEntity(InputUserRequestDto inputUserRequestDto);

    User updateUserRequestDtoToEntity(UpdateUserRequestDto updateUserRequestDto);

}
