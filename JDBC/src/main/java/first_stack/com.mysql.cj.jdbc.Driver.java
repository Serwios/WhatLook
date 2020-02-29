package first_stack;

import java.sql.*;
import java.util.Scanner;

class first_stack{

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/new?useLegacyDatetimeCode=false&amp&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement statement;

        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM movies";

        ResultSet resultSet = statement.executeQuery(sql);

        Scanner in = new Scanner(System.in);
        System.out.print("Input a film: ");
        String film = in.nextLine();

        while (resultSet.next()) {

            String names = resultSet.getString("Name");
            String Director = resultSet.getString("Director");
            int year = resultSet.getInt("Year");
            String Country = resultSet.getString("Country");

             if(film.equals(names)){

                 int id = resultSet.getInt("ID");
                 System.out.println("\n================\n");
                 System.out.println("id: " + id);
                 System.out.println("Name: " + names);
                 System.out.println("Director: " + Director);
                 System.out.println("Year: " + year);
                 System.out.println("Country: " + Country);
             }

        }


        System.out.println("\n");
        System.out.println("Did you want to look on DB?");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        answer=answer.toLowerCase();

          if (answer.equals("так") || answer.equals("yes")){
              System.out.println("Okay");

              while (resultSet.next()) {

                  int id = resultSet.getInt("ID");
                  String name = resultSet.getString("Name");
                  String Director = resultSet.getString("Director");
                  int year = resultSet.getInt("Year");
                  String Country = resultSet.getString("Country");

                  System.out.println("\n================\n");
                  System.out.println("id: " + id);
                  System.out.println("Name: " + name);
                  System.out.println("Director: " + Director);
                  System.out.println("Year: " + year);
                  System.out.println("Country: " + Country);
              }
          }
          else {
              System.out.println("This is end");
          }


        in.close();
        input.close();
        resultSet.close();
        statement.close();
        connection.close();
    }
}