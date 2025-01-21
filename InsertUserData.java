import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserData {
    public static void main(String[] args) {
        // パスワードをエンクリプト
        String rawPassword1 = "password1";
        String encryptedPassword1 = BCrypt.hashpw(rawPassword1, BCrypt.gensalt());

        String rawPassword2 = "password2";
        String encryptedPassword2 = BCrypt.hashpw(rawPassword2, BCrypt.gensalt());

        // データベース接続設定
        String url = "jdbc:h2:tcp://192.168.3.100:9092/~/test"; // H2 データベースの接続URL
        String username = "sa"; // データベースのユーザー名
        String password = ""; // データベースのパスワード（空の場合）

        // SQLクエリ
        String sql = "MERGE INTO users (id, username, email, password, roles, created_at, updated_at) " +
                     "KEY (username) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // SQL文を準備
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ID、ユーザー名、メールアドレス、エンクリプトされたパスワード、役割を設定
            pstmt.setString(1, java.util.UUID.randomUUID().toString()); // ランダムなUUIDをIDに使用
            pstmt.setString(2, "user1");
            pstmt.setString(3, "user1@example.com");
            pstmt.setString(4, encryptedPassword1); // エンクリプトされたパスワードを設定
            pstmt.setString(5, "USER");

            // SQLを実行してデータを挿入または更新
            pstmt.executeUpdate();

            // 2番目のユーザーのデータも挿入または更新
            pstmt.setString(1, java.util.UUID.randomUUID().toString());
            pstmt.setString(2, "user2");
            pstmt.setString(3, "user2@example.com");
            pstmt.setString(4, encryptedPassword2); // エンクリプトされたパスワードを設定
            pstmt.setString(5, "ADMIN");

            pstmt.executeUpdate();

            System.out.println("データが正常に挿入または更新されました。");

        } catch (SQLException e) {
            System.err.println("SQLエラーが発生しました: " + e.getMessage());
        }
    }
}





