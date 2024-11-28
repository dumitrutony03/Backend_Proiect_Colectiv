package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

import java.util.List;

@Service
public class UserService {
    @Autowired
    MongoTemplate mt;

    public UserData save(UserData user) {
        return mt.save(user);
    }

    public List<UserData> findAll() {
        return mt.findAll(UserData.class);
    }

    public UserData findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, UserData.class);
    }

    public UserData loginDoctor(UserData model) {
        try{
            Query query = getQuery(model);
            return mt.findOne(query, Doctors.class);
        }catch (Exception e ){
            return null;
        }
    }

    public UserData loginPacient(UserData model) {
        try{
            Query query = getQuery(model);
            return mt.findOne(query, Doctors.class);
        }catch (Exception e ){
            return null;
        }
    }

    private static Query getQuery(UserData model) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName")
                .is(model.getUserName())
                .andOperator(Criteria.where("password")
                        .is(model.getPassword())));
        return query;
    }
}

