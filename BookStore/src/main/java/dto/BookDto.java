package dto;

import org.springframework.web.multipart.MultipartFile;

public class BookDto {
    private MultipartFile file;

    private String tensach;
    private String tacgia;
    private String theloai;

    private Integer muc;


    private String detail;

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public Integer getMuc() {
        return muc;
    }

    public void setMuc(Integer muc) {
        this.muc = muc;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
