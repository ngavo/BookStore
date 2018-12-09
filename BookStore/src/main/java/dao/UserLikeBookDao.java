package dao;

import dto.UserLikeBook;
import model.LikeBook;
import org.springframework.data.repository.CrudRepository;

public interface UserLikeBookDao extends CrudRepository<LikeBook, String> {
}
