import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BDConnection {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection("jdbc:postgresql://dbIP/DB",
                    "user", "password");
            PreparedStatement stmt = con.prepareStatement("query");
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> validEmails = new ArrayList<>();
            while (rs.next())
                validEmails.add(rs.getString(2));

            validEmails.forEach(System.out::println);



        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
