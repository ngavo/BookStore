package Controller;

import dao.UserDao;
import dao.UserReponsitory;
import dto.DataTokenAndIdUser;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    private UserReponsitory userReponsitory;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser(){
        return (List<User>) userDao.findAll();
    }


    @RequestMapping(value="/register" ,method=RequestMethod.POST)
    public ResponseEntity<String> InserUser(@ModelAttribute User user) throws IOException {



        return new ResponseEntity<String>(userReponsitory.InserUser(user), HttpStatus.OK);

    }


    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DataTokenAndIdUser> Login(@RequestBody User use)
    {
        if(userReponsitory.Login(use)==null)
        {
            return new ResponseEntity<DataTokenAndIdUser>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<DataTokenAndIdUser>(userReponsitory.Login(use),HttpStatus.OK);
    }




}
