package week2exercise;
import java.io.*;

public class Caching {
    public String createFile(String name){
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()){
                System.out.println("File Created: " + name);
            } else {
                System.out.println("File exists: " + name);
            }
            return name;
        } catch (IOException e){
            System.out.println("FILE Creation ERROR");
            e.printStackTrace();
            return "0";
        }
    }
    
    public void writeToFile(String domain, String[] IPs, String name){
        String fileName = createFile(name);
        if ("0".equals(fileName)){
            System.out.println("File creation Failed, aborting write operation");
            return;
        }
        
        try (FileWriter wr = new FileWriter(fileName)){
            StringBuilder construct = new StringBuilder(domain + ": ");
            for (int i = 0; i < IPs.length; i++) {
                construct.append(IPs[i]);
                if (i < IPs.length - 1) {
                    construct.append(", ");
                }
            }
            wr.write(construct.toString());
            
        } catch (IOException e) {
            System.out.println("An error occured while writing to the file");
            e.printStackTrace();
        }
    }
    
    public String searchFile(String URL, String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = br.readLine()) != null){
                if (line.startsWith(URL + ":")){
                    return line.substring((URL + ": ").length());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured while reading the file");
            e.printStackTrace();
        }
        return "Not Found";
    }
}
