package com.denis.api.modules.user.mapper;

import com.denis.api.modules.user.factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserMapperTest {
    @Test
    @DisplayName("Must convert CreateUserRequest to entity")
    void mustConvertCreateUserRequestToEntity(){
        var request = UserFactory.getCreateUserRequest();

        var entity = CreateUserMapper.toEntity(request, request.getPassword());

        assertEquals(request.getName(), entity.getName());
        assertEquals(request.getEmail(), entity.getEmail());
        assertEquals(request.getPassword(), entity.getPassword());
    }
    @Test
    @DisplayName("Must convert entity to CreateUserResponse")
    void mustConvertEntityToCreateUserResponse(){
        var entity = UserFactory.getUser();

        var response = CreateUserMapper.toResponse(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getEmail(), response.getEmail());
    }
}
