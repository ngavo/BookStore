package Service;

import Config.CloudinaryConfig;
import com.cloudinary.utils.ObjectUtils;
import dao.BookDao;
import dao.UserLikeBookDao;
import dto.BookDto;
import model.Book;
import com.cloudinary.Cloudinary;
import model.LikeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class BookServiceImp implements IBookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserLikeBookDao userLikeBookDao;


    @Autowired
    private MongoOperations mongoOperations;


    @Override
    public Book insertBook(BookDto book) throws IOException {

        Cloudinary c = new  Cloudinary(ObjectUtils.asMap(
                "cloud_name", "drpjudkfr",
                "api_key", "661119547315821",
                "api_secret", "mmp9IUQgnBMmYPEXlLgpN93SYgw"
        ));

        File f =  Files.createTempFile("temp", book.getFile().getOriginalFilename()).toFile();
        book.getFile().transferTo(f);
        Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
        String k=  (String)response.get("url");

        Book bookObject = new Book();

        bookObject.setTensach(book.getTensach());
        bookObject.setTacgia(book.getTacgia());
        bookObject.setTheloai(book.getTheloai());
        bookObject.setMuc(book.getMuc());
        bookObject.setDetail(book.getDetail());
        bookObject.setImage(k);

        return bookDao.save(bookObject);
    }


    public LikeBook inserLikeBook(LikeBook like){

        LikeBook likeBook = new LikeBook();
        likeBook.setBook_id(like.getBook_id());
        likeBook.setUse_id(like.getUse_id());

        return userLikeBookDao.save(likeBook);

    }

    public LikeBook deleteLikeBook(LikeBook deletelikebook){

        Query query = new Query();
        //query.addCriteria(Criteria.where("book_id").is(deletelikebook.getBook_id()).andOperator(Criteria.where("use_id").is(deletelikebook.getUse_id())));

        query.addCriteria(Criteria.where("book_id").is(deletelikebook.getBook_id()).and("use_id").is(deletelikebook.getUse_id()));

        return mongoOperations.findAndRemove(query,LikeBook.class);


    }


}
