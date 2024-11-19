package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    MongoTemplate mt;

    public User save(User user) {
        return mt.save(user);
    }

    public List<User> findAll() {
        return mt.findAll(User.class);
    }

    public User findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, User.class);
    }

//    public User login(User model) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is(userName));
//
//        return mt.findOne(query, User.class);
//    }

    public User login(User model) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName")
                        .is(model.getUserName())
                        .andOperator(Criteria.where("password")
                                .is(model.getPassword())));
        return mt.findOne(query, User.class);
    }
}

