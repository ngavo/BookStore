package Service;

import dto.BookDto;
import model.Book;
import model.LikeBook;

import java.io.IOException;

public interface IBookService {

    public Book insertBook(BookDto book) throws IOException;
    public LikeBook inserLikeBook(LikeBook like);
    public LikeBook deleteLikeBook(LikeBook deletelikebook);

}
