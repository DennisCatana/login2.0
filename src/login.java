import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class login {
    private JTextField usuario;
    private JButton OKButton;
    private JPasswordField clave;
    private JPanel login;
    private JPanel rootPanel;
    static final String DB_URL="jdbc:mysql://localhost/Universidad";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY="Select Nombre, Clave From Estudiantes;";
    private String userName;
    private String claveL;

    public login(){
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobar();
                String username = usuario.getText();
                String claveU = clave.getText();
            }
        });

    }




























    public static void comprobar(){
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY);
        ){
            while (rs.next()){
                System.out.println("\n----------------------------------------------");
                System.out.println("Nombre: "+rs.getString("Nombre"));
                /*System.out.println("id: "+rs.getInt("id"));
                System.out.println("Ciudad: "+rs.getString("Ciudad"));
                System.out.println("Edad: "+rs.getInt("Edad"));
                System.out.println("Cédula: "+rs.getInt("Cedula"));*/
                System.out.println("clave"+rs.getString("Clave"));

            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

