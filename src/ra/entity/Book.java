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
    public void inputData(Scanner scanner, Book[] arrBook, int numberBook) {
        // 1. Mã sách duy nhất
        boolean checkBookId = true;
        System.out.println("Nhập vào mã sách:");
        do {
            this.bookId = scanner.nextLine();
            //Kiểm tra mã sách là duy nhất,gồm 4 ký tự, bắt đầu là B
            boolean isBookIdCheck = false;
            for (int i = 0; i < numberBook; i++) {
                if (arrBook[i].bookId.equals(this.bookId)) {
                    isBookIdCheck = true;
                    break;
                }
            }
            if (isBookIdCheck) {
                //Mã sách bị trùng lặp
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
            } else {
                //Mã sách không bị trùng lặp
                //Kiểm tra độ dài mã sách = 4
                if (this.bookId.length() == 4) {
                    //Kiểm tra mã sách bắt đầu là ký tự B
                    if (this.bookId.charAt(0) == 'B') {
                        break;
                    } else {
                        System.err.println("Mã sách bắt đầu là ký tự B, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Mã sách phải gồm 4 ký tự, vui lòng nhập lại");
                }
            }
        } while (checkBookId);

        // 2.Tên sách, không được trùng lặp.
        boolean checkBookName = true;
        System.out.print("Nhập tên sách: ");
        do {
            this.bookName = scanner.nextLine();
            boolean isCheckBookName = false;
            //Kiểm tra tên sách là duy nhất
            for (int i = 0; i < numberBook; i++) {
                if (arrBook[i].bookName.equals(this.bookName)) {
                    isCheckBookName = true;
                    break;
                }
            }
            if (!isCheckBookName) {
                //Tên sách không bị trùng lặp
                break;
            } else {
                //Tên sách bị trùng lặp
                System.err.println("Tên sách đã tồn tại, vui lòng nhập lại");
            }
        } while (checkBookName);
        //3. Giá nhập của sách có giá trị lớn hơn 0
        boolean checkImportPrice = true;
        System.out.println(" Giá nhập của sách : ");
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("Giá sách phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (checkImportPrice);
        //4. Giá xuất của sách, có giá trị lớn hơn ít
        //nhất 30% so với giá nhập
        boolean checkExportPrice = true;
        System.out.println("Giá xuất của sách :");
        do {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice >= this.importPrice * 1.3) {
                break;
            } else {
                System.err.println("Giá xuất phải có giá trị lớn hơn 30% so với giá nhập, vui lòng nhập lại");
            }
        } while (checkExportPrice);
        //5.Tác giả, có từ 6-50 ký tự
        boolean checkAuthor = true;
        System.out.println("Nhập vào tên tác  giả của sách :");
        do {
            this.author = scanner.nextLine();
            if (this.author.length() >= 6 && this.author.length() <= 50) {
                break;
            } else {
                System.err.println("Tên tác giả từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (checkAuthor);
        //6. Năm xuất bản, ít nhất xuất bản sau năm 2000
        boolean checkYear = true;
        System.out.println("Nhập năm xuất bản : ");
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            if (this.year >= 2000) {
                break;
            } else {
                System.err.println("Năm xuất bản phải sau năm 2000, vui lòng nhập lại");
            }
        } while (checkYear);
    }
        public void displayData(){
            System.out.printf("Mã sách:%-15sTên sách:%-15sGiá nhập:%-25.1fGiá xuất:%-25.1fLợi nhuận:%-20.1fTên tác giả:%-15sNăm xuất bản:%-20d\n", this.bookId, this.bookName, this.importPrice, this.exportPrice, this.interest, this.author, this.year);
    }
    public void calInterrest(){
        this.interest=this.exportPrice-this.importPrice;
    }
}
