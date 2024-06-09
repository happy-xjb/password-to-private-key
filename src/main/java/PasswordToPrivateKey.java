import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author happyxjb
 * @date 2024/6/10 00:26
 */
public class PasswordToPrivateKey {
    public static String passwordToPrivateKey(String password) {
        try {
            // 获取 SHA-256 消息摘要实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 对输入的密码进行哈希处理
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // 将哈希值转换为十六进制表示的字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你的密码: ");
        String password = scanner.nextLine();

        String privateKey = passwordToPrivateKey(password);
        System.out.println("生成的以太坊私钥为: " + privateKey);

        scanner.close();
    }
}
