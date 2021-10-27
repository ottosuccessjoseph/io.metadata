package io.metadata.identity.domain.mapper;

import io.metadata.identity.domain.dto.CreateUserRequest;
import io.metadata.identity.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToUser(CreateUserRequest dto);
}
