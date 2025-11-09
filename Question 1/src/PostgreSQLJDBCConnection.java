import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBCConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Assignment%203";
    private static final String USER ="postgres";
    private static final String PASSWORD = "database";

    //Function to connect to the students database
    public static Connection getConnection(){ 
        try {
            Class.forName("org.postgresql.Driver");
            //Connect to database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Function to retrieve all students from the students table and display it in the terminal
    public static void getAllStudents(){
        String sql = "SELECT * FROM students";

        try(Connection conn = getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql)){

                while(rs.next()){
                    System.out.println(rs.getInt("student_id") + " | " +
                                        rs.getString("first_name") + " | " +
                                        rs.getString("last_name") + " | " +
                                        rs.getString("email") + " | " +
                                        rs.getString("enrollment_date"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    //Function to add a new student to the students table
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?,?,?,?)";

        try (Connection conn = getConnection();
            PreparedStatement state = conn.prepareStatement(sql)){

                state.setString(1, first_name);
                state.setString(2, last_name);
                state.setString(3, email);

                java.sql.Date sqlDate = java.sql.Date.valueOf(enrollment_date);
                state.setDate(4, sqlDate);

                int rows = state.executeUpdate();
                System.out.println(rows + " student inserted successfully.");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Function to update a student's email based on their student_id
    public static void updateStudentEmail(int student_id, String new_email){ 
        String sql = "UPDATE students SET email = ? WHERE student_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement state = conn.prepareStatement(sql)){

            state.setString(1, new_email);
            state.setInt(2, student_id);

            int rows = state.executeUpdate();
            System.out.println(rows + " student's email updated successfully");
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Function to delete a student from the students table based on their student_id
    public static void deleteStudent(int student_id){
        String sql = "DELETE FROM students WHERE student_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement state = conn.prepareStatement(sql)){
            
            state.setInt(1, student_id);
            
            int rows = state.executeUpdate();
            System.out.println(rows + " student deleted successfully.");      

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


