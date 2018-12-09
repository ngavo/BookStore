package dao;

import dto.SearchObject;
import dto.UserLikeBook;
import model.Book;
import model.LikeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookQuery implements BookQueryInterface {

    @Autowired
    private MongoOperations mongoOperations;




    public ArrayList<UserLikeBook> findBookWithMuc(int muc, String idUser){

        Query query = new Query();
        Query queryboos = new Query();

        List<LikeBook> booklike =  mongoOperations.find(query.addCriteria(Criteria.where("use_id").is(idUser)), LikeBook.class);

        List<Book> books = mongoOperations.find(queryboos.addCriteria(Criteria.where("muc").is(muc)),Book.class);

        ArrayList<UserLikeBook> userLikeBooks = new ArrayList<UserLikeBook>();
        for (Book b : books ) {
            UserLikeBook userLikeBook = new UserLikeBook();
            userLikeBook.setBook(b);
            userLikeBook.setLike(false);
            for (LikeBook lb: booklike) {
                if(lb.getBook_id().equals(b.getId())){
                    userLikeBook.setLike(true);
                }
            }

            userLikeBooks.add(userLikeBook);
        }


        return userLikeBooks;


    }

    public  List<Book> findSachWithName(SearchObject name){
       //Query query = new Query();
        BasicQuery query = new BasicQuery("{ tensach : {$regex:/"+name.getName()+"/i}}");
        //query.addCriteria(Criteria.where("tensach").regex("/"+name.getName()+"/i"));
        return mongoOperations.find(query,Book.class);
    }




}
