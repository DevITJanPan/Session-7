package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    //1. Fields-Attributes: Tính bao đóng - private
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;
    //2. Constructors
    //2.1.Default
    public Book(){
    }
    //2.2. Khởi tạo tất cả thông tin book
    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }
    //3. Methods
    //3.1. Getter/Setter

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {

        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    //3.2. Phương thức nhập dữ liệu book
    public void inputData(Scanner scanner, Book[] arrBook, int index) {
        System.out.println("Nhập vào mã sách:");
        boolean checkBookId = true;
        do {
            this.bookId = scanner.nextLine();
            if (index!=0) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrBook[i].bookId.equals(this.bookId)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist){
                    break;
                }else{
                    System.err.println("Mã sinh viên đã tồn tại, vui lòng nhập lại");
                }
            }else{
                break;
            }
        }while(checkBookId);

        // Tên sách, không được trùng lặp, gồm 4
        //ký tự, bắt đầu là ký tự B
        System.out.print("Nhập tên sách: ");
        boolean checkBookName;
        do {
            this.bookName = scanner.nextLine();
            checkBookName = validBookName(this.bookName) && uniqueBookName(arrBook, index, this.bookName);
            if (!checkBookName) {
                System.err.println("Vui lòng nhập đúng định dạng hoặc tên sách đã tồn tại");
            }
        } while (!checkBookName);

        System.out.print("Nhập giá nhập của sách: ");
        boolean checkImportPrice;
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            checkImportPrice = validateImportPrice(this.importPrice);
            if (!checkImportPrice) {
                System.err.println("Vui lòng nhập giá sách lớn hơn 0");
            }
        } while (!checkImportPrice);

        System.out.print("Nhập giá xuất của sách: ");
        boolean checkExportPrice;
        do {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            checkExportPrice = validateExportPrice(this.exportPrice, this.importPrice);
            if (!checkExportPrice) {
                System.err.println("Giá xuất phải lớn hơn ít nhất 30% so với giá nhập");
            }
        } while (!checkExportPrice);

        System.out.print("Nhập tên tác giả: ");
        boolean checkAuthor;
        do {
            this.author = scanner.nextLine();
            checkAuthor = validateAuthor(this.author);
            if (!checkAuthor) {
                System.err.println("Vui lòng nhập tên tác giả chứa 6 - 50 ký tự");
            }
        } while (!checkAuthor);

        System.out.print("Nhập năm xuất bản: ");
        boolean checkYear;
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            checkYear = validateYear(this.year);
            if (!checkYear) {
                System.err.println("Vui lòng nhập năm xuất bản sau năm 2000");
            }
        } while (!checkYear);
    }

    public void displayData(Scanner scanner, Book[] arrBook, int index) {
        System.out.printf("%-15s%-15s%-25.1f%-25.1f%-20.1f%-15s%-20d\n", this.bookId, this.bookName, this.importPrice, this.exportPrice, this.interest, this.author, this.year);
    }
    public void calinterest(){
        this.interest = (this.exportPrice-this.importPrice);
    }

    // check uniqueBookId
    public boolean uniqueBookId(Book[] arrBook, int index, String bookId) {
        for (int i = 0; i < index; i++) {
            if (arrBook[i].bookId.equals(this.bookId)) {
                return false;
            }
        }
        return true;
    }

    // check bookNameRegex and uniqueBookName
    public boolean validBookName(String bookName) {
        String bookNameRegex = "^[B][a-z0-9_-]{3}$";
        return Pattern.matches(bookNameRegex, bookName);
    }

    public boolean uniqueBookName(Book[] arrBook, int index, String bookName) {
        for (int i = 0; i < index; i++) {
            if (arrBook[i].bookName.equals(this.bookName)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateImportPrice(Float importPrice) {
        if (this.importPrice > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateExportPrice(Float exportPrice, Float importPrice) {
        //: Giá xuất của sách, có giá trị lớn hơn ít
        //nhất 30% so với giá nhập
        //        Float isCheck = (this.importPrice * 1.3F);
        if (this.exportPrice >= (this.importPrice * 1.3)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAuthor(String author) {
        // Tác giả, có từ 6-50 ký tự
        String authorRegex = "^(?=.{6,50}$)[A-Za-zÀ-ỹ][A-Za-zÀ-ỹ ]*$";
        return Pattern.matches(authorRegex, author);
    }

    public boolean validateYear(int year) {
        // Năm xuất bản, ít nhất xuất bản sau năm 2000
        if (this.year >= 2000) {
            return true;
        } else {
            return false;
        }
    }
}
