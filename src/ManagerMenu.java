

import java.util.Scanner;

public class ManagerMenu {
    public void display(){
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("관리자 메뉴");
            System.out.println("1. 제품추가");
            System.out.println("2. 제품초기화");
            System.out.println("3. 제품조회");
            System.out.println("4. 종료");
            System.out.print("원하는 기능 입력: ");
            int functions = sc.nextInt();

            if (functions == 1) {
                ProductInfo productinfo = new ProductInfo();
                productinfo.addProduct();
            } else if (functions == 2) {
                ProductInfoDel productInfoDel = new ProductInfoDel();
                productInfoDel.delProduct();
            } else if (functions == 3) {
                ProductScan productScan = new ProductScan();
                productScan.scanProduct(true);
            }else if (functions == 4) {
                System.out.println("프로그램 종료");
                sc.close();
                System.exit(0); // 종료
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력하세요");
            }
        }
    }
}