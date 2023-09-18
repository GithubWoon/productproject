import java.util.Scanner;

public class BuyerMenu {
	public void display() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("구매자 메뉴");
			System.out.println("1. 상품목록조회");
			System.out.println("2. 장바구니");
			System.out.println("3. 구매");
			System.out.println("4. 종료");
			System.out.print("원하시는 기능 입력하세요: ");
			int functions = sc.nextInt();
			
			if(functions == 1) {
				ProductScan productScan = new ProductScan();
				productScan.scanProduct(false);
			}else if(functions == 2) {
				ShopBasket shopBasket = new ShopBasket();
				shopBasket.reserve();
			}else if(functions == 3) {
				BuyerPur buyerPur = new BuyerPur();
				buyerPur.display();
			}else if(functions == 4) {
				System.out.println("프로그램 종료");
				sc.close();
                System.exit(0); // 종료
			}else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
				continue;
				
			}
		}
	}

}
