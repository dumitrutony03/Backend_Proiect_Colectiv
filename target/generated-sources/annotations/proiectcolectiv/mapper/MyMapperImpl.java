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
    date = "2024-11-30T18:49:15+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class MyMapperImpl implements MyMapper {

    @Override
    public Appointments toModel(AppointmentsDto source) {
        if ( source == null ) {
            return null;
        }

        Appointments appointments = new Appointments();

        appointments.setId( source.getId() );
        appointments.setDoctorUsername( source.getDoctorUsername() );
        appointments.setPacientUsername( source.getPacientUsername() );
        appointments.setDate( source.getDate() );
        appointments.setTime( source.getTime() );
        appointments.setDiagnosis( source.getDiagnosis() );

        return appointments;
    }

    @Override
    public AppointmentsDto toDto(Appointments source) {
        if ( source == null ) {
            return null;
        }

        AppointmentsDto appointmentsDto = new AppointmentsDto();

        appointmentsDto.setId( source.getId() );
        appointmentsDto.setDoctorUsername( source.getDoctorUsername() );
        appointmentsDto.setPacientUsername( source.getPacientUsername() );
        appointmentsDto.setDate( source.getDate() );
        appointmentsDto.setTime( source.getTime() );
        appointmentsDto.setDiagnosis( source.getDiagnosis() );

        return appointmentsDto;
    }

    @Override
    public Doctors toModel(DoctorsDto source) {
        if ( source == null ) {
            return null;
        }

        Doctors doctors = new Doctors();

        doctors.setId( source.getId() );
        doctors.setUserName( source.getUserName() );
        doctors.setPassword( source.getPassword() );
        doctors.setPacients( pacientDtoListToPacientList( source.getPacients() ) );
        doctors.setHospitals( hospitalsDtoListToHospitalsList( source.getHospitals() ) );
        doctors.setSpeciality( source.getSpeciality() );
        doctors.setRating( source.getRating() );

        doctors.setRole( myToEnumDoctor() );

        return doctors;
    }

    @Override
    public DoctorsDto toDto(Doctors source) {
        if ( source == null ) {
            return null;
        }

        DoctorsDto doctorsDto = new DoctorsDto();

        doctorsDto.setId( source.getId() );
        doctorsDto.setPacients( pacientListToPacientDtoList( source.getPacients() ) );
        doctorsDto.setHospitals( hospitalsListToHospitalsDtoList( source.getHospitals() ) );
        doctorsDto.setSpeciality( source.getSpeciality() );
        doctorsDto.setRating( source.getRating() );
        doctorsDto.setUserName( source.getUserName() );
        doctorsDto.setPassword( source.getPassword() );

        return doctorsDto;
    }

    @Override
    public Hospitals toModel(HospitalsDto source) {
        if ( source == null ) {
            return null;
        }

        Hospitals hospitals = new Hospitals();

        hospitals.id = source.getId();
        hospitals.name = source.getName();
        hospitals.adress = source.getAdress();
        hospitals.latitude = source.getLatitude();
        hospitals.longitude = source.getLongitude();
        hospitals.reviews = toModelList( source.getReviews() );

        return hospitals;
    }

    @Override
    public HospitalsDto toDto(Hospitals source) {
        if ( source == null ) {
            return null;
        }

        HospitalsDto hospitalsDto = new HospitalsDto();

        hospitalsDto.setId( source.id );
        hospitalsDto.setName( source.name );
        hospitalsDto.setAdress( source.adress );
        hospitalsDto.setLatitude( source.latitude );
        hospitalsDto.setLongitude( source.longitude );
        hospitalsDto.setReviews( toDtoList( source.reviews ) );

        return hospitalsDto;
    }

    @Override
    public Pacient toModel(PacientDto source) {
        if ( source == null ) {
            return null;
        }

        Pacient pacient = new Pacient();

        pacient.setId( source.getId() );
        pacient.setUserName( source.getUserName() );
        pacient.setPassword( source.getPassword() );

        pacient.setRole( myToEnumPacient() );

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

        pacientDto.setId( source.getId() );
        pacientDto.setUserName( source.getUserName() );
        pacientDto.setPassword( source.getPassword() );

        return pacientDto;
    }

    @Override
    public Reviews toModel(ReviewsDto source) {
        if ( source == null ) {
            return null;
        }

        Reviews reviews = new Reviews();

        reviews.setId( source.getId() );
        reviews.setReview_text( source.getReview_text() );
        reviews.setRating( source.getRating() );

        return reviews;
    }

    @Override
    public ReviewsDto toDto(Reviews source) {
        if ( source == null ) {
            return null;
        }

        ReviewsDto reviewsDto = new ReviewsDto();

        reviewsDto.setId( source.getId() );
        reviewsDto.setReview_text( source.getReview_text() );
        reviewsDto.setRating( source.getRating() );

        return reviewsDto;
    }

    protected List<Pacient> pacientDtoListToPacientList(List<PacientDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Pacient> list1 = new ArrayList<Pacient>( list.size() );
        for ( PacientDto pacientDto : list ) {
            list1.add( toModel( pacientDto ) );
        }

        return list1;
    }

    protected List<Hospitals> hospitalsDtoListToHospitalsList(List<HospitalsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Hospitals> list1 = new ArrayList<Hospitals>( list.size() );
        for ( HospitalsDto hospitalsDto : list ) {
            list1.add( toModel( hospitalsDto ) );
        }

        return list1;
    }

    protected List<PacientDto> pacientListToPacientDtoList(List<Pacient> list) {
        if ( list == null ) {
            return null;
        }

        List<PacientDto> list1 = new ArrayList<PacientDto>( list.size() );
        for ( Pacient pacient : list ) {
            list1.add( toDto( pacient ) );
        }

        return list1;
    }

    protected List<HospitalsDto> hospitalsListToHospitalsDtoList(List<Hospitals> list) {
        if ( list == null ) {
            return null;
        }

        List<HospitalsDto> list1 = new ArrayList<HospitalsDto>( list.size() );
        for ( Hospitals hospitals : list ) {
            list1.add( toDto( hospitals ) );
        }

        return list1;
    }
}
