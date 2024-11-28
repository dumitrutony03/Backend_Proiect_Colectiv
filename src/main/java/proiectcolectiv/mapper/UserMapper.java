package proiectcolectiv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Role;
import proiectcolectiv.model.UserData;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", expression = "java(myToEnum(source.role))")
    UserData toModel(UserDataDto source);

    @Mapping(target = "role", expression = "java(myToString(source.role))")
    UserDataDto toDto(UserData source);

    default String myToString(Role role) {
        return role.name();
    }

    default Role myToEnum(String role) {
        return Role.valueOf(role);
    }
}
