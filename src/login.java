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
    static final String QUERY="Select Nombre, Clave From Estudiantes ;";
    public static String Nombrei;
    public static String Clavei;
    public static String usuariox;
    public static String clavex;

    public login(){
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox =usuario.getText().trim();
                clavex = new String(clave.getPassword()).trim();
                comprobar();
            }
        });

    }





    public static void comprobar(){
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY);
        ){
            boolean userFound = false;
            while (rs.next()){

                Nombrei = rs.getString("Nombre");
                Clavei = rs.getString("Clave");
                if(Nombrei.equals(usuariox) && Clavei.equals(clavex)){
                    userFound = true;
                    System.out.println("\n----------------------------------------------");
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                /*System.out.println("id: "+rs.getInt("id"));
                System.out.println("Ciudad: "+rs.getString("Ciudad"));
                System.out.println("Edad: "+rs.getInt("Edad"));
                System.out.println("Cédula: "+rs.getInt("Cedula"));*/
                    System.out.println("Clave: "+rs.getString("Clave"));
                }}
            if (!userFound){
                    System.out.println("!!ERRORR¡¡ Usuario o clave incorrectos.\"");
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

