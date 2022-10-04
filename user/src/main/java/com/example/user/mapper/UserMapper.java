package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.dto.InputUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id",ignore = true)
    User inputUserRequestDtoToEntity(InputUserRequestDto inputUserRequestDto);



}
