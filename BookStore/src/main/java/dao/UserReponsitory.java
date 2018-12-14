package dao;

import dto.DataTokenAndIdUser;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserReponsitory {

    @Autowired
    private UserDao userDao;

    @Autowired
    TokenReponsitory tokenReponsitory;

    @Autowired
    private MongoTemplate mongoTemplate;

    public DataTokenAndIdUser InserUser(User user){
        User k = userDao.save(user);

        DataTokenAndIdUser us = new DataTokenAndIdUser();
         String t = tokenReponsitory.createToken(k.getId());

         us.setToken(t);
         us.setIdUser(k.getId());
         us.setName(k.getName());
         return us;

    }



    public DataTokenAndIdUser Login(User us)
    {
        User u = mongoTemplate.findOne(new Query(Criteria.where("name").is(us.getName())), User.class);



        if(u!=null)
        {
            if(u.getPass().equals(us.getPass()))
            {
                DataTokenAndIdUser result = new DataTokenAndIdUser();
                result.setToken(tokenReponsitory.createToken(u.getId()));
                result.setIdUser(u.getId());
                result.setName(u.getName());
                return result ;
            }

        }

        return null;
    }



}
