package proiectcolectiv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proiectcolectiv.dto.*;
import proiectcolectiv.model.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyMapper {
    //Mapper for Appointments, AppointmentsDto
    Appointments toModel(AppointmentsDto source);
    AppointmentsDto toDto(Appointments source);

    //Mapper for Doctors, DoctorsDto
    Doctors toModel(DoctorsDto source);
    DoctorsDto toDto(Doctors source);

    //Mapper for Hospitals, HospitalsDto
    Hospitals toModel(HospitalsDto source);
    HospitalsDto toDto(Hospitals source);

    //Mapper for Pacient, PacientDto
    Pacient toModel(PacientDto source);

    List<Reviews> toModelList(List<ReviewsDto> source);
    List<ReviewsDto> toDtoList(List<Reviews> source);

    PacientDto toDto(Pacient source);

    //Mapper for Reviews, ReviewsDto
    Reviews toModel(ReviewsDto source);
    ReviewsDto toDto(Reviews source);
}
