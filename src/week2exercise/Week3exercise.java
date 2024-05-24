package week2exercise;
import java.net.*;
import java.io.*;

public class Week3exercise {

    public static void main(String[] args) throws IOException {
        System.out.println("Program starting . . .");
        String filename = "IP-Addresses.txt";
        Caching cache = new Caching();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Domain: ");
        String domain = br.readLine();
        
        String result = (cache.searchFile(domain, filename));
        if (result.equals("Not Found")) {
            InetAddress[] addresses = InetAddress.getAllByName(domain);
            String[] IPs = new String[addresses.length];
            for (int i = 0; i < addresses.length; i++) {
                IPs[i] = addresses[i].getHostAddress();
            }
            cache.writeToFile(domain, IPs, filename);
            System.out.println("IP addresses for: " + domain + ": " + String.join(", ", IPs));
        } else {
            System.out.println("Cached IP Addresses for " + domain + ": " + result);
        }
    }
    
}
