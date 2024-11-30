package proiectcolectiv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.UserData;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:15:34+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserData toModel(UserDataDto source) {
        if ( source == null ) {
            return null;
        }

        UserData userData = new UserData();

        userData.userName = source.userName;
        userData.password = source.password;

        userData.role = myToEnum(source.role);

        return userData;
    }

    @Override
    public UserDataDto toDto(UserData source) {
        if ( source == null ) {
            return null;
        }

        UserDataDto userDataDto = new UserDataDto();

        userDataDto.userName = source.userName;
        userDataDto.password = source.password;

        userDataDto.role = myToString(source.role);

        return userDataDto;
    }
}
