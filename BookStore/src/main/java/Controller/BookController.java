package Controller;

import Service.IBookService;
import dao.BookDao;
import dao.BookQueryInterface;
import dto.BookDto;
import dto.SearchObject;
import dto.UserLikeBook;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private IBookService iBookService;


    @Autowired
    private BookQueryInterface bookQueryInterface;


    @GetMapping
    public List<Book> getAllBook(){
        return (List<Book>) bookDao.findAll();
    }

    @PostMapping
    public Book inserBook(@ModelAttribute BookDto book) throws IOException {
        return iBookService.insertBook(book);
    }

    @GetMapping("/muc")
    public List<UserLikeBook> getBookMuc0(@RequestParam int idmuc, @RequestParam String iduser){
        return bookQueryInterface.findBookWithMuc(idmuc,iduser);
    }

    @PostMapping("/search")
    public  List<Book> findSachWithName(@RequestBody SearchObject name){

        return bookQueryInterface.findSachWithName(name);

    }
}
