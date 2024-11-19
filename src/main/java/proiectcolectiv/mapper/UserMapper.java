package proiectcolectiv.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import proiectcolectiv.dto.UserDto;
import proiectcolectiv.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserDto source);
    UserDto toDto(User source);
}
