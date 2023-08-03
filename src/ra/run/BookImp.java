package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    static Book[] arrBook = new Book[100];//Toàn cục
    static int numberBook = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("****************MENU*****************");
            System.out.println("1. Nhập thông tin n sách");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.println("Lựa chọn của bạn :");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookImp.inputListBook(scanner);
                    break;
                case 2:
                    BookImp.calListinterest();
                    break;
                case 3:
                    BookImp.displayListBook();
                    break;
                case 4:
                    BookImp.sortBookByExportPriceASC();
                    break;
                case 5:
                    BookImp.sortBookByInterestDESC();
                    break;
                case 6:
                    BookImp.searchBookByName(scanner);
                    break;
                case 7:
                    BookImp.getNumberBookByYear(scanner);
                    break;
                case 8:
                    BookImp.getNumberBookByAuthor(scanner);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn từ 1-9");

            }

        } while (true);
    }

    public static void inputListBook(Scanner scanner) {
        System.out.println("Nhập vào số sách cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrBook[numberBook] = new Book();
            arrBook[numberBook].inputData(scanner, arrBook, numberBook);
            numberBook++;
        }
    }

    public static void calListinterest() {
        for (int i = 0; i < numberBook; i++) {
            arrBook[i].calInterrest();
        }
    }

    public static void displayListBook() {
        for (int i = 0; i < numberBook; i++) {
            arrBook[i].displayData();
        }
    }

    public static void sortBookByExportPriceASC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getExportPrice() > arrBook[j].getExportPrice()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong sách theo giá bán tăng dần");
    }

    public static void sortBookByInterestDESC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getInterest() < arrBook[j].getInterest()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong sách theo lợi nhuận giảm dần");
    }

    public static void searchBookByName(Scanner scanner) {
        System.out.println("Nhập vào tên sách cần tìm kiếm : ");
        String bookNameSearch = scanner.nextLine();
        boolean isNameSearch = false;
        System.out.println("Thông tin các sách tìm kiếm: ");
        for (int i = 0; i < numberBook; i++) {
            if (arrBook[i].getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())) {
                arrBook[i].displayData();
                isNameSearch = true;
            }
        }
        if (!isNameSearch) {
            System.out.println("Không tìm thấy sách");
        }
    }

    public static void getNumberBookByYear(Scanner scanner) {
        int[] yearsBook = new int[numberBook];
        int cnt = 0;
        for (int i = 0; i < numberBook - 1; i++) {
            boolean isYears = false;
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getYear() == arrBook[j].getYear()) {
                    isYears = true;
                    break;
                }
            }
            if (!isYears) {
                //year của sách thứ i không trùng với các sách khác
                yearsBook[cnt] = arrBook[i].getYear();
                cnt++;
            }
        }
        yearsBook[cnt] = arrBook[numberBook - 1].getYear();
        //Thống kê sách theo năm
        int[] arrNumberBookByYear = new int[cnt + 1];
        for (int i = 0; i <= cnt; i++) {
            int numberBookByYear = 0;
            for (int j = 0; j < numberBook; j++) {
                if (yearsBook[i] == arrBook[j].getYear()) {
                    numberBookByYear++;
                }
            }
            arrNumberBookByYear[i] = numberBookByYear;
        }
        //In thống kê
        System.out.println("Thống kê số lượng sách theo năm xuất bản:");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d", yearsBook[i]);
        }
        System.out.printf("\n");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d", arrNumberBookByYear[i]);
        }
        System.out.printf("\n");
    }

    public static void getNumberBookByAuthor(Scanner scanner) {
        String[] arrAuthor = new String[numberBook];
        int cnt = 0;
        for (int i = 0; i < numberBook; i++) {
            //Kiểm tra  xem có bị trùng hay không, nếu trùng không lưu, ngược thì lưu
            boolean isCheckAuthor = false;
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getAuthor().toLowerCase().contains(arrBook[j].getAuthor())) {
                    isCheckAuthor = true;
                    break;
                }
            }
            //Không trùng --> lưu vào mảng arrAuthor
            if (!isCheckAuthor) {
                arrAuthor[cnt] = arrBook[i].getAuthor();
                cnt++;
            }
        }
        //Khai báo mảng chứa số lần xuất hiện của từng tác giả
        int[] arrCountAuthor = new int[numberBook];
        for (int i = 0; i < numberBook; i++) {
            int indexCountAuthor = 0;
            for (int j = 0; j < numberBook; j++) {
                if (arrAuthor[i].contains(arrBook[j].getAuthor())) {
                    indexCountAuthor++;
                }
                arrCountAuthor[i] = indexCountAuthor;
            }
        }
        // Display statistic info
        for (int i = 0; i < numberBook; i++) {
            System.out.printf("Tác giả %s có số lượng sách là %d\n", arrAuthor[i], arrCountAuthor[i]);
        }
    }
}
