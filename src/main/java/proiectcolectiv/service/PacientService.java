package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

import java.util.List;

@Service
public class PacientService {
    @Autowired
    MongoTemplate mt;

    /**
     * Saves a Pacient to the database.
     *
     * @param pacient the pacient to save
     * @return the saved pacient
     */
    public Pacient save(Pacient pacient) {
        return mt.save(pacient);
    }

    /**
     * Retrieves the last pacient ID from the database.
     *
     * @return the last pacient ID
     */
    public int getLasId() {
        int lastID = 0;
        List<Pacient> list = findAll();
        int size = list.size();

        if (size > 0) {
            lastID = list.stream().toList().get(size - 1).getId();
        }
        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    /**
     * Finds a pacient by their username.
     *
     * @param userName the username of the pacient
     * @return the pacient if found, otherwise null
     */
    public Pacient findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, Pacient.class);
    }

    /**
     * Retrieves all pacients from the database.
     *
     * @return a list of all pacients
     */
    public List<Pacient> findAll() {
        return mt.findAll(Pacient.class);
    }

    /**
     * Checks if a pacient exists based on the provided UserDataDto.
     *
     * @param model the UserDataDto containing the username
     * @return true if the pacient exists, otherwise false
     */
    public boolean checkPacientExists(UserDataDto model) {
        Pacient pacient = findByUserName(model.userName);
        if (pacient == null) {
            return false;
        }
        String name = pacient.getUserName();
        boolean val = name.isEmpty();
        return !val; // return true if name is not empty
    }

    /**
     * Adds a diagnostic to the pacient's record.
     *
     * @param userName the username of the pacient
     * @param diagnostic the diagnostic to add
     * @return the updated pacient, or null if the pacient is not found
     */
    public Pacient addDiagnostic(String userName, String diagnostic) {
        Pacient pacient = findByUserName(userName);
        if (pacient == null) {
            return null;
        }
        pacient.addDiagnostic(diagnostic);
        return save(pacient);
    }
}
