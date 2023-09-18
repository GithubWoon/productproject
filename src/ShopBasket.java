
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShopBasket {
	
	public void reserve() {
	    String jdbcUrl = "jdbc:oracle:thin:@192.168.0.86:1521:XE"; // Oracle 데이터베이스 URL
	    String username = "project2"; // 데이터베이스 사용자 이름
	    String password = "project2"; // 데이터베이스 비밀번호

	    Scanner sc = new Scanner(System.in);
	    System.out.print("상품명: ");
	    String productName = sc.nextLine();
	    System.out.print("구매 개수: ");
	    int purchaseProduct = sc.nextInt();

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

	        // 정보 조회
	        String selectSql = "SELECT * FROM productInfo WHERE productName = ?";
	        PreparedStatement selectStatement = connection.prepareStatement(selectSql);
	        selectStatement.setString(1, productName);
	        ResultSet resultSet = selectStatement.executeQuery();

	        if (resultSet.next()) {
	            int currentQuantity = resultSet.getInt("quantity");
	            if (currentQuantity >= purchaseProduct) {
	                // 구매 가능 경우

	                // shopbasket 테이블에 데이터 삽입
	                String insertSql = "INSERT INTO shopbasket (userid, username, productname, quantity) VALUES (?,?, ?, ?)";
	                PreparedStatement insertStatement = connection.prepareStatement(insertSql);
	                insertStatement.setString(1, "shopbasket"); // 사용자 ID를 "shopbasket"으로 설정
	                insertStatement.setString(2, "shopbasket"); // 사용자 ID를 "shopbasket"으로 설정
	                insertStatement.setString(3, productName);
	                insertStatement.setInt(4, purchaseProduct);
	                insertStatement.executeUpdate();
	                insertStatement.close();

	                System.out.println("상품 구매 완료!!");
	                System.out.println("구매 제품명: " + productName);
	                System.out.println("구매수: " + purchaseProduct);
	            } else {
	                System.out.println("재고 수 부족!");
	                System.out.println("다음에 구매해주세요");
	            }
	        } else {
	            System.out.println("상품이 데이터베이스에 존재하지 않음");
	        }
	        // 리소스 해제
	        resultSet.close();
	        selectStatement.close();
	        connection.close();

	        BuyerMenu buyerMenu = new BuyerMenu();
	        buyerMenu.display();

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        sc.close();
	    }
	}

}
