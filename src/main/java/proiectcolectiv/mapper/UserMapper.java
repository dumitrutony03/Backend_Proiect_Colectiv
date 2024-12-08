package proiectcolectiv.mapper;

import org.mapstruct.Mapper;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserData toModel(UserDataDto source);

    UserDataDto toDto(UserData source);

    Doctors toModelDoctors(UserDataDto source);

    Pacient toModelPacient(UserDataDto source);


}
