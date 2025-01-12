package proiectcolectiv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-12T02:07:46+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserData toModel(UserDataDto source) {
        if ( source == null ) {
            return null;
        }

        UserData userData = new UserData();

        userData.setUserName( source.userName );
        userData.setPassword( source.password );
        userData.setRole( source.role );

        return userData;
    }

    @Override
    public UserDataDto toDto(UserData source) {
        if ( source == null ) {
            return null;
        }

        UserDataDto userDataDto = new UserDataDto();

        userDataDto.userName = source.getUserName();
        userDataDto.password = source.getPassword();
        userDataDto.role = source.getRole();

        return userDataDto;
    }

    @Override
    public Doctors toModelDoctors(UserDataDto source) {
        if ( source == null ) {
            return null;
        }

        Doctors doctors = new Doctors();

        doctors.setUserName( source.userName );
        doctors.setPassword( source.password );
        doctors.setRole( source.role );

        return doctors;
    }

    @Override
    public Pacient toModelPacient(UserDataDto source) {
        if ( source == null ) {
            return null;
        }

        Pacient pacient = new Pacient();

        pacient.setUserName( source.userName );
        pacient.setPassword( source.password );
        pacient.setRole( source.role );

        return pacient;
    }
}
