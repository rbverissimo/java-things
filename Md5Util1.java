import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plainText = args[0];

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();

        // Convert byte array to a hexadecimal String
        StringBuilder sb = new StringBuilder(2 * digest.length);
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        System.out.println("MD5 Hash: " + sb);
    }
}