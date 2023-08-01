package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    public static void main(String[] args) {
        Book[] arrBook=new Book[100];
        Scanner scanner =new Scanner(System.in);
        int index=0;
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
            int choice=Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Số sách  cần nhập :");
                    int n=Integer.parseInt(scanner.nextLine());
                    //Nhập thông tin cho n book
                    for (int i = 0; i < n; i++) {
                        arrBook[index]=new Book();
                        arrBook[index].inputData(scanner,arrBook,index);
                        index++;
                        break;
                    }
                    break;
                case 2:
                    for (int i = 0; i < index; i++) {
                    arrBook[i].calinterest();
                    }
                    System.out.println();
                    break;
                case 3:
                    //Hiển thị thông tin book
                    System.out.println("Thông tin sách :");
                    for (int i = 0; i < index; i++) {
                     arrBook[i].displayData(scanner,arrBook,index);
                    }
                    break;
                case 4:
                    //Sử dụng thuật toán selection sort để sắp xếp
                    for (int i = 0; i < index-1; i++) {
                        for (int j = i+1; j < index; j++) {
                            if(arrBook[i].getExportPrice()>arrBook[j].getExportPrice()){
                                //Đổi chỗ 2 phần tử
                                Book bookTemp=arrBook[i];
                                arrBook[i]=arrBook[j];
                                arrBook[j]=bookTemp;
                            }
                        }
                    }
                    System.out.println();
                    break;
                case 5:
                    //Sử dụng thuật toán selection sort để sắp xếp
                    for (int i = 0; i < index-1; i++) {
                        for (int j = i+1; j < index; j++) {
                            if(arrBook[i].getInterest()<arrBook[j].getInterest()){
                                //Đổi chỗ 2 phần tử
                                Book bookTemp=arrBook[i];
                                arrBook[i]=arrBook[j];
                                arrBook[j]=bookTemp;
                            }
                        }
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Nhập vào tên sách cần tìm:");
                    String nameSearch= scanner.nextLine();
                    boolean isCheck=false;
                    System.out.println("Sách tìm thấy:");
                    for (int i = 0; i <index; i++) {
                        if(arrBook[i].getBookName().toLowerCase().contains(nameSearch.toLowerCase())) {
                            arrBook[i].displayData(scanner, arrBook, index);
                            isCheck = true;
                        }
                    }
                    if(!isCheck){
                        System.out.println("Không có sách thỏa mãn điều kiện tìm kiếm");
                    }
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn từ 1-9");

            }

        }while (true);

    }
}
