import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuFinPartida extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel ganador;
    private JButton revancha,salir,menu;
    public static void main(String[] args) {
        
    }

    public MenuFinPartida(Jugador jug1, Jugador jug2,String nombreganador,JFrame frame) {
        panel = new JPanel();
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(650,300,400,500);
        this.setVisible(true);
        this.add(panel);
        panel.setLayout(null);
        this.setResizable(false);
        ganador=new JLabel("Ganador: "+nombreganador);
        ganador.setBounds(100, 10, 200, 50);
        revancha=new JButton("Revancha");
        revancha.setBounds(100, 90, 200, 50);
        menu=new JButton("Menu");
        menu.setBounds(100, 180, 200, 50);
        salir=new JButton("Salir");
        salir.setBounds(100, 270, 200, 50);
        
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("fuente/Odachi.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            
            ganador.setFont(font.deriveFont(Font.BOLD, 25));
            revancha.setFont(font.deriveFont(Font.BOLD, 25));
            menu.setFont(font.deriveFont(Font.BOLD, 25));
            salir.setFont(font.deriveFont(Font.BOLD, 25));
            

        } catch (FontFormatException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        panel.add(ganador);
        panel.add(revancha);
        panel.add(menu);
        panel.add(salir);
        salir.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
    
        });
        
        menu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                frame.dispose();
                new Inicio();
               
            }
    
        });
        revancha.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                dispose();
                Jugador Jugador1 = new Jugador(jug1.nombre, crearClase(jug1.clase.nombre));
                Jugador Jugador2 = new Jugador(jug2.nombre, crearClase(jug2.clase.nombre));
                new Arena(Jugador1, Jugador2);
                
            }

        });

    
    }

    public Clase crearClase(String nombre){
        Clase clase = new Clase();
        switch (nombre) {
            case "JIN KAZAMA":
            clase = new Jin();
            break;
            case "CHUN LI":
            clase = new Chun();
            break;
            case "ZANGIEF":
            clase = new Zangief();
            break;
            case "M. BISON":
            clase = new Bison();
            break;
    
        default:
            break;
            
    }
     
    return clase;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
