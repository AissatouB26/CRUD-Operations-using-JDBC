public class Main {
    public static void main(String[] args) {
        PostgreSQLJDBCConnection.getAllStudents();
        System.out.println(" ");
         
        PostgreSQLJDBCConnection.addStudent("Aissatou", "Barry", "aissatou.barry@example.com", "2024-09-01");
        PostgreSQLJDBCConnection.getAllStudents();
        System.out.println(" ");
        
        PostgreSQLJDBCConnection.updateStudentEmail(9, "aiss.barry@example.com");
        PostgreSQLJDBCConnection.getAllStudents();
        System.out.println(" ");

        PostgreSQLJDBCConnection.deleteStudent(9);
        PostgreSQLJDBCConnection.getAllStudents();
        
    }

}
