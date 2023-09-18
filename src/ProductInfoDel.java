
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductInfoDel {
    public void delProduct() {
        String jdbcUrl = "jdbc:oracle:thin:@192.168.0.86:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project2"; // 데이터베이스 사용자 이름
        String password = "project2"; // 데이터베이스 비밀번호
        String tableName = "productinfo";

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("상품을 초기화 하시겠습니까(Y/N): ");
            String answer = sc.nextLine();

            if (answer.equals("Y")) {
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    String deleteSql = "DELETE FROM " + tableName;
                    PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("테이블의 모든 레코드가 삭제되었습니다.");
                    } else {
                        System.out.println("테이블에 레코드가 없거나 삭제 중 오류가 발생했습니다.");
                    }

                    preparedStatement.close();
                    connection.close();
                    
                    ManagerMenu managerMenu = new ManagerMenu();
                    managerMenu.display();
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            } else if (answer.equals("N")) {
                System.out.println("초기화 취소되었습니다.");
                break;
            } else {
                System.out.println("다시 입력하세요");
            }
        }
    }

}