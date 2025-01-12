package proiectcolectiv.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import proiectcolectiv.dto.AppointmentsDto;
import proiectcolectiv.dto.DoctorsDto;
import proiectcolectiv.dto.HospitalsDto;
import proiectcolectiv.dto.PacientDto;
import proiectcolectiv.dto.ReviewsDto;
import proiectcolectiv.model.Appointments;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Hospitals;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.Reviews;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T19:06:48+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MyMapperImpl implements MyMapper {

    @Override
    public Appointments toModel(AppointmentsDto source) {
        if ( source == null ) {
            return null;
        }

        Appointments appointments = new Appointments();

        appointments.id = source.id;
        appointments.doctorUsername = source.doctorUsername;
        appointments.pacientUsername = source.pacientUsername;
        appointments.date = source.date;
        appointments.begin = source.begin;
        appointments.end = source.end;

        return appointments;
    }

    @Override
    public AppointmentsDto toDto(Appointments source) {
        if ( source == null ) {
            return null;
        }

        AppointmentsDto appointmentsDto = new AppointmentsDto();

        appointmentsDto.id = source.id;
        appointmentsDto.doctorUsername = source.doctorUsername;
        appointmentsDto.pacientUsername = source.pacientUsername;
        appointmentsDto.date = source.date;
        appointmentsDto.begin = source.begin;
        appointmentsDto.end = source.end;

        return appointmentsDto;
    }

    @Override
    public Doctors toModel(DoctorsDto source) {
        if ( source == null ) {
            return null;
        }

        Doctors doctors = new Doctors();

        doctors.id = source.id;
        doctors.userName = source.userName;
        doctors.password = source.password;
        List<String> list = source.hospitals;
        if ( list != null ) {
            doctors.hospitals = new ArrayList<String>( list );
        }
        doctors.speciality = source.speciality;
        doctors.reviews = toModelList( source.reviews );

        return doctors;
    }

    @Override
    public DoctorsDto toDto(Doctors source) {
        if ( source == null ) {
            return null;
        }

        DoctorsDto doctorsDto = new DoctorsDto();

        doctorsDto.id = source.id;
        doctorsDto.speciality = source.speciality;
        doctorsDto.userName = source.userName;
        doctorsDto.password = source.password;
        doctorsDto.reviews = toDtoList( source.reviews );
        List<String> list1 = source.hospitals;
        if ( list1 != null ) {
            doctorsDto.hospitals = new ArrayList<String>( list1 );
        }

        return doctorsDto;
    }

    @Override
    public Hospitals toModel(HospitalsDto source) {
        if ( source == null ) {
            return null;
        }

        Hospitals hospitals = new Hospitals();

        hospitals.id = source.id;
        hospitals.name = source.name;
        hospitals.adress = source.adress;
        hospitals.latitude = source.latitude;
        hospitals.longitude = source.longitude;
        hospitals.reviews = toModelList( source.reviews );

        return hospitals;
    }

    @Override
    public HospitalsDto toDto(Hospitals source) {
        if ( source == null ) {
            return null;
        }

        HospitalsDto hospitalsDto = new HospitalsDto();

        hospitalsDto.id = source.id;
        hospitalsDto.name = source.name;
        hospitalsDto.adress = source.adress;
        hospitalsDto.latitude = source.latitude;
        hospitalsDto.longitude = source.longitude;
        hospitalsDto.reviews = toDtoList( source.reviews );

        return hospitalsDto;
    }

    @Override
    public Pacient toModel(PacientDto source) {
        if ( source == null ) {
            return null;
        }

        Pacient pacient = new Pacient();

        pacient.id = source.id;
        pacient.userName = source.userName;
        pacient.password = source.password;
        List<String> list = source.diagnostics;
        if ( list != null ) {
            pacient.diagnostics = new ArrayList<String>( list );
        }

        return pacient;
    }

    @Override
    public List<Reviews> toModelList(List<ReviewsDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Reviews> list = new ArrayList<Reviews>( source.size() );
        for ( ReviewsDto reviewsDto : source ) {
            list.add( toModel( reviewsDto ) );
        }

        return list;
    }

    @Override
    public List<ReviewsDto> toDtoList(List<Reviews> source) {
        if ( source == null ) {
            return null;
        }

        List<ReviewsDto> list = new ArrayList<ReviewsDto>( source.size() );
        for ( Reviews reviews : source ) {
            list.add( toDto( reviews ) );
        }

        return list;
    }

    @Override
    public PacientDto toDto(Pacient source) {
        if ( source == null ) {
            return null;
        }

        PacientDto pacientDto = new PacientDto();

        pacientDto.id = source.id;
        pacientDto.userName = source.userName;
        pacientDto.password = source.password;
        List<String> list = source.diagnostics;
        if ( list != null ) {
            pacientDto.diagnostics = new ArrayList<String>( list );
        }

        return pacientDto;
    }

    @Override
    public Reviews toModel(ReviewsDto source) {
        if ( source == null ) {
            return null;
        }

        Reviews reviews = new Reviews();

        reviews.id = source.id;
        reviews.review_text = source.review_text;
        reviews.rating = source.rating;

        return reviews;
    }

    @Override
    public ReviewsDto toDto(Reviews source) {
        if ( source == null ) {
            return null;
        }

        ReviewsDto reviewsDto = new ReviewsDto();

        reviewsDto.id = source.id;
        reviewsDto.review_text = source.review_text;
        reviewsDto.rating = source.rating;

        return reviewsDto;
    }
}
