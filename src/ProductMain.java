import java.sql.*;
import java.util.Scanner;

public class ProductMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean validCorrect = false;
        String role = null;
        String buyer = null;

        String jdbcUrl = "jdbc:oracle:thin:@192.168.0.86:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project2"; // 데이터베이스 사용자 이름
        String password = "project2"; // 데이터베이스 비밀번호

        while (!validCorrect) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    // 로그인 기능 (이미 구현된 코드 사용)
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("PASSWORD: ");
                    String pw = sc.nextLine();

                    try {
                        // JDBC 드라이버 로드
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        // 데이터베이스 연결
                        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                        // 사용자 입력한 ID와 비밀번호를 사용하여 데이터베이스에서 사용자 정보 조회
                        String selectSql = "SELECT username FROM users WHERE userid = ? AND password = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                        preparedStatement.setString(1, id);
                        preparedStatement.setString(2, pw);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            String userName = resultSet.getString("username");
                            role = resultSet.getString("username");
                            buyer = resultSet.getString("username");
                            System.out.println("사용자 이름: " + userName);
                            validCorrect = true;
                        } else {
                            System.out.println("일치하는 사용자가 없습니다.");
                        }

                        // 리소스 해제
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // 회원가입 기능
                    System.out.print("ID: ");
                    String newId = sc.nextLine();
                    System.out.print("이름: ");
                    String newUserName = sc.nextLine();
                    System.out.print("PASSWORD: ");
                    String newPw = sc.nextLine();

                    try {
                        // JDBC 드라이버 로드
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        // 데이터베이스 연결
                        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                        // 새로운 사용자를 데이터베이스에 추가
                        String insertSql = "INSERT INTO users(userid, username, password) VALUES (?, ?, ?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
                        insertStatement.setString(1, newId);
                        insertStatement.setString(2, newUserName);
                        insertStatement.setString(3, newPw);

                        int rowsInserted = insertStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("회원가입이 완료되었습니다.");
                        } else {
                            System.out.println("회원가입에 실패하였습니다.");
                        }

                        // 리소스 해제
                        insertStatement.close();
                        connection.close();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("올바른 선택을 하세요.");
                    break;
            }
        }

        if ("탁창우".equals(role) || "조경래".equals(role) || "김태운".equals(role)){
            ManagerMenu managerMenu = new ManagerMenu();
            managerMenu.display();
        } else {
            BuyerMenu buyerMenu = new BuyerMenu();
            buyerMenu.display();
        }

        sc.close();
    }
}
