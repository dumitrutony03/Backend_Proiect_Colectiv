package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private MongoTemplate mt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserData save(UserData user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UserData loginDoctor(UserData model) {
        UserData user = findByUserName(model.getUserName());
        if (user != null && validatePassword(model.getPassword(), user.getPassword())) {
            return user; // Login successful
        }
        return null; // Login failed
    }

    public UserData loginPacient(UserData model) {
        UserData user = findByUserName(model.getUserName());
        if (user != null && validatePassword(model.getPassword(), user.getPassword())) {
            return user; // Login successful
        }
        return null; // Login failed
    }
}
