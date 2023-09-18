

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductInfo {
    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        String jdbcUrl = "jdbc:oracle:thin:@192.168.0.86:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project2"; // 데이터베이스 사용자 이름
        String password = "project2"; // 데이터베이스 비밀번호

        System.out.print("제품번호: ");
        String productNumber = sc.nextLine(); // 사용자가 입력한 제품 번호
    	

        String selectSql = "SELECT * FROM productInfo WHERE productNumber = ?";
        String updateSql = "UPDATE productInfo SET quantity = ? WHERE productNumber = ?";
        String insertSql = "INSERT INTO productInfo (productNumber,productName,quantity) VALUES (?,?,?)";
        

        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // 상품존재여부 확인
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setString(1, productNumber);
            ResultSet resultSet = selectStatement.executeQuery();
            
            if(resultSet.next()) {
            	System.out.println("해당 번호와 동일한 상품이 있습니다.");
            	System.out.print("수량: ");
                int quantity = sc.nextInt(); // 사용자가 입력한 수량
            	int currentQuantity = resultSet.getInt("quantity");
            	int newQuantity = currentQuantity + quantity;
            	
            	PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            	updateStatement.setInt(1, newQuantity);
            	updateStatement.setString(2, productNumber);
            	// SQL 문 실행
                int rowsAffected = updateStatement.executeUpdate();
                // 결과 확인
            	if (rowsAffected > 0) {
            		System.out.println("데이터베이스에 데이터가 성공적으로 삽입되었습니다.");
            	} else {
            		System.out.println("데이터베이스에 데이터를 삽입하는 데 문제가 발생했습니다.");
            	}
            	 updateStatement.close();
            }else {
            	System.out.println("해당 제품번호가 데이터베이스에 존재하지 않습니다.");
            	System.out.print("제품이름: ");
                String productName = sc.nextLine(); // 사용자가 입력한 제품 이름
                System.out.print("수량: ");
                int quantity = sc.nextInt(); // 사용자가 입력한 수량
            	// PreparedStatement 생성
            	PreparedStatement preparedStatement = connection.prepareStatement(insertSql);	
            	preparedStatement.setString(1, productNumber);
            	preparedStatement.setString(2, productName);
            	preparedStatement.setInt(3, quantity);


            	// SQL 문 실행
            	int rowsAffected = preparedStatement.executeUpdate();

            	// 결과 확인
            	if (rowsAffected > 0) {
            		System.out.println("데이터베이스에 데이터가 성공적으로 삽입되었습니다.");
            	} else {
            		System.out.println("데이터베이스에 데이터를 삽입하는 데 문제가 발생했습니다.");
            	}
            	preparedStatement.close();
            }
            
            // 리소스 해제
            resultSet.close();
            selectStatement.close();
            connection.close();
            
            ManagerMenu managerMenu = new ManagerMenu();
            managerMenu.display();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
        return;
    }
}