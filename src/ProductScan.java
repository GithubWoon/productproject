
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductScan {
	public void scanProduct(boolean isManager) {
		String jdbcUrl = "jdbc:oracle:thin:@192.168.0.86:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project2"; // 데이터베이스 사용자 이름
        String password = "project2"; // 데이터베이스 비밀번호
        
        try {
        	 Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        	 String tableName = "productInfo";
        	 String scan = "SELECT * FROM "+ tableName;
        	 PreparedStatement preparedStatement = connection.prepareStatement(scan);
             ResultSet resultSet = preparedStatement.executeQuery();
             
             while (resultSet.next()) {
                 // 테이블의 열 데이터 가져오기
                 int productNumber = resultSet.getInt("productNumber");
                 String productName = resultSet.getString("productName");
                 int quantity = resultSet.getInt("quantity");
                 // 필요한 작업 수행
                 System.out.println("상품번호: " + productNumber + ", 상품명: " + productName + ", 재고량: " +quantity );
             }
             
             resultSet.close();
             preparedStatement.close();
             connection.close();
             
             if (isManager) {
                 ManagerMenu managerMenu = new ManagerMenu();
                 managerMenu.display();
             } else {
                 BuyerMenu buyerMenu = new BuyerMenu();
                 buyerMenu.display();
             }
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
