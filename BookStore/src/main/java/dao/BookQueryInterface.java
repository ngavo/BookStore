package dao;

import dto.SearchObject;
import dto.UserLikeBook;
import model.Book;
import model.LikeBook;

import java.util.ArrayList;
import java.util.List;

public interface BookQueryInterface {
    public ArrayList<UserLikeBook> findBookWithMuc(int muc, String idUser);
    public  List<Book> findSachWithName(SearchObject name);
}
