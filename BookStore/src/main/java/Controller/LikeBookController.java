package Controller;

import Service.IBookService;
import model.LikeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeBookController {

    @Autowired
    private IBookService iBookService;


    @PostMapping("/insert")
    public boolean inserLikeBook(@RequestBody LikeBook like){
        try{

            iBookService.inserLikeBook(like);
            return true;

        }catch (Exception e){
            return false;
        }
    }


    @PostMapping("/delete")
    public boolean deleteLikeBook(@RequestBody LikeBook deletelikebook){
        try {

            iBookService.deleteLikeBook(deletelikebook);
            return true;


        }catch (Exception ex){
            return false;
        }
    }


}
