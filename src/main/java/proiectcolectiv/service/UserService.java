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

    /**
     * Saves a UserData object to the database.
     *
     * @param user the UserData object to save
     * @return the saved UserData object
     */
    public UserData save(UserData user) {
        return mt.save(user);
    }

    /**
     * Retrieves all UserData objects from the database.
     *
     * @return a list of all UserData objects
     */
    public List<UserData> findAll() {
        return mt.findAll(UserData.class);
    }

    /**
     * Finds a UserData object by its username.
     *
     * @param userName the username to search for
     * @return the UserData object if found, or null if not
     */
    public UserData findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, UserData.class);
    }

    /**
     * Attempts to log in a doctor by matching the username and password.
     *
     * @param model the UserData object containing the username and password
     * @return the corresponding Doctor object if login is successful, or null if not
     */
    public UserData loginDoctor(UserData model) {
        try {
            Query query = getQuery(model);
            return mt.findOne(query, Doctors.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Attempts to log in a pacient by matching the username and password.
     *
     * @param model the UserData object containing the username and password
     * @return the corresponding Pacient object if login is successful, or null if not
     */
    public UserData loginPacient(UserData model) {
        try {
            Query query = getQuery(model);
            return mt.findOne(query, Pacient.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Helper method to create a query based on the username and password.
     *
     * @param model the UserData object containing the username and password
     * @return a Query object for querying the database
     */
    private static Query getQuery(UserData model) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName")
                .is(model.getUserName())
                .andOperator(Criteria.where("password")
                        .is(model.getPassword())));
        return query;
    }
}
