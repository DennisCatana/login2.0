import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    private JTextField usuario;
    private JButton OKButton;
    private JPasswordField clave;
    private JPanel login;
    private JButton borrarButton;
    private JButton actualizarButton;
    private JButton agregarButton;
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
                conectar();
                usuariox =usuario.getText().trim();
                clavex = new String(clave.getPassword()).trim();
                comprobar();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox =usuario.getText().trim();
                clavex = new String(clave.getPassword()).trim();
                eliminar(usuariox);
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox =usuario.getText().trim();
                clavex = new String(clave.getPassword()).trim();
                agregar(usuariox, clavex);

            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox =usuario.getText().trim();
                clavex = new String(clave.getPassword()).trim();
                actualizar(clavex);
            }
        });

    }

    public static void agregar(String usu, String clax){
        String query3 = " insert into Estudiantes values('"+usu+"','"+clax+"')";
        //System.out.println(query3);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query3);
            System.out.println("Usuario nuevito xd");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
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
                   // System.out.println("----------------------------------------------");
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                    System.out.println("Clave: "+rs.getString("Clave"));
                    System.out.println("----------------------------------------------");
                }}
            if (!userFound){
                 System.out.println("----------------------------------------------");
                 System.out.println("!!ERRORR¡¡ Usuario o clave incorrectos.");
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static void eliminar(String usu){
        String query2 = "DELETE FROM Estudiantes where Nombre = '"+ usu +"'";
        //System.out.println(query2);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            System.out.println("Usuario morido xd");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void actualizar(String clave){
        String query2 = "UPDATE Estudiantes set Clave = '"+1234+"'"+ "where Nombre="+'"'+ "Dennis" +'"' ;
        //System.out.println(query2);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            System.out.println("Usuario Actualizado xd");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }
    public static void conectar(){
        try{
            final String DB_URL="jdbc:mysql://localhost/Universidad";
            final String USER="root";
            final String PASS="root_bas3";
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
            Statement stmt= conn.createStatement();

        }catch(SQLException S){
            JOptionPane.showMessageDialog(null,S.getMessage(),"ERROR DE CONEXION",JOptionPane.ERROR_MESSAGE);
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

