package az.mm.jasypt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author MM <mushfiqazeri@gmail.com>
 */
public class DecryptJson {

    public static void main(String[] args) {
        String encryptedJson = readJsonFromUrl("http://localhost:1717/currency/rates");
        String json = decrypt(encryptedJson);
        
        System.out.println("encryptedJson: " + encryptedJson);
        System.out.println("decryptedJson:  " + json);
    }


    private static String decrypt(String text) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(TestJasypt.getPassword());
        String decryptedText = textEncryptor.decrypt(text);

        return decryptedText;
    }

    
    private static String readJsonFromUrl(String url) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = new URL(url).openStream();
             BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {

            int cp;
            while ((cp = rd.read()) != -1) 
                sb.append((char) cp);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
    
}
