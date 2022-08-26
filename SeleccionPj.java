import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class SeleccionPj extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton iniciarpartida;
    private String primerJugador,segundoJugador;
    private JLabel gifear,gifear2, titulo,jugador1text,jugador2text,estadisticas,estadisticas2;
    private Clip clip;
    private ArrayList<Boton> jugador1botones,jugador2botones;
    private Jugador Jugador1,Jugador2;
    private Font font;
    

    public SeleccionPj(String nombre1, String nombre2)  {
        try {
            playMusic();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        panel = new JPanel();
        this.add(panel);
        jugador1botones= new ArrayList<Boton>();
        jugador2botones= new ArrayList<Boton>();
        jugador1text = new JLabel(nombre1,SwingConstants.CENTER);
        jugador2text = new JLabel(nombre2,SwingConstants.CENTER);
        gifear2 = new JLabel();
        gifear2.setBounds(1020, 320, 600, 600);
        gifear2.setVisible(true);
        panel.add(gifear2);
        gifear = new JLabel();
        gifear.setBounds(150, 320, 600, 600);
        gifear.setVisible(true);
        panel.add(gifear);
        estadisticas2= new JLabel();
        estadisticas2.setBounds(1430, 300, 150, 500);
        estadisticas= new JLabel();
        estadisticas.setBounds(10, 300, 150, 500);
        panel.add(estadisticas);
        panel.add(estadisticas2);
        for (int i=0;i<4;i++){
            switch (i) {
                    case 0:
                    jugador1botones.add(new Boton("JIN KAZAMA",""));
                    break;
                    case 1:
                    jugador1botones.add(new Boton("M. BISON",""));
                    break;
                    case 2:
                    jugador1botones.add(new Boton("ZANGIEF",""));
                    break;
                    case 3:
                    jugador1botones.add(new Boton("CHUN LI",""));
                    break;
            
                default:
                    break;
            }
            
        }
        int x=180;
        int y=260;
        for (int i=0;i<jugador1botones.size();i++){
            jugador1botones.get(i).setBounds(x, y, 160, 50);
            x+=170;
            if(i==1){
                x=180;
                y=330;
            }
            jugador1botones.get(i).addActionListener(this);
            panel.add(jugador1botones.get(i));
        }

        x=1060;
        y=260;
        for (int i=0;i<4;i++){
           
                switch (i) {
                        case 0:
                        jugador2botones.add(new Boton("JIN KAZAMA","REVERSED"));
                        break;
                        case 1:
                        jugador2botones.add(new Boton("M. BISON","REVERSED"));
                        break;
                        case 2:
                        jugador2botones.add(new Boton("ZANGIEF","REVERSED"));
                        break;
                        case 3:
                        jugador2botones.add(new Boton("CHUN LI","REVERSED"));
                        break;
                
                    default:
                        break;
                }
                
            
           
        }

        for (int i=0;i<jugador2botones.size();i++){
            jugador2botones.get(i).setBounds(x, y, 160, 50);
            x+=170;
            if(i==1){
                x=1060;
                y=330;
            }
            jugador2botones.get(i).addActionListener(this);

            panel.add(jugador2botones.get(i));
        }
        
        
        
        jugador1text.setBounds(270, 200, 160, 50);
        jugador2text.setBounds(1150, 200, 160, 50);
        
        
        iniciarpartida = new JButton("Iniciar");
        panel.add(iniciarpartida);
        this.setTitle("SeleccionPJ");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        this.setSize(1600, 900);
        this.setVisible(true);
        
        titulo = new JLabel("SELECCIONAR PERSONAJE");
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("fuente/Odachi.ttf"));

            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            titulo.setFont(font.deriveFont(Font.BOLD,100));
            titulo.setForeground(Color.WHITE);
            jugador1text.setForeground(Color.WHITE);
            jugador2text.setForeground(Color.WHITE);
            iniciarpartida.setFont(font.deriveFont(Font.BOLD,40));
           
            iniciarpartida.setBorder(new LineBorder(Color.GRAY,3));
            
            iniciarpartida.setForeground(Color.BLACK);
            iniciarpartida.setOpaque(true);
            for (int i=0;i<jugador1botones.size();i++){
                jugador1botones.get(i).setFont(font.deriveFont(Font.BOLD,25));
                jugador2botones.get(i).setFont(font.deriveFont(Font.BOLD,25));
                jugador1botones.get(i).setBorder(new LineBorder(Color.BLACK,3));
                jugador2botones.get(i).setBorder(new LineBorder(Color.BLACK,3));
                jugador1botones.get(i).setForeground(Color.black);
                jugador2botones.get(i).setForeground(Color.black);
            }
          
            jugador1text.setFont(font.deriveFont(Font.BOLD,40));
            jugador2text.setFont(font.deriveFont(Font.BOLD,40));
        } catch (FontFormatException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        this.setResizable(false);
        
        iniciarpartida.setBounds(1600 / 2 - 90, 900/2+100, 200, 80);
        
        panel.add(jugador1text);
        panel.add(jugador2text);
        
        panel.add(iniciarpartida);
        panel.add(titulo);
        titulo.setBounds(1600 / 2 - 400, 0, 800, 150);
        ImageIcon imagen = new ImageIcon("gif/SeleccionPJ.gif");
        Image imagen2 = imagen.getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT);
        imagen = new ImageIcon(imagen2);
        JLabel fondo = new JLabel();
        fondo.setIcon(imagen);
        panel.add(fondo);
        fondo.setBounds(0, 0, 1600, 900);
        iniciarpartida.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                stopmusic();
                dispose();
                Jugador1= new Jugador(nombre1, crearClase(primerJugador));
                Jugador2= new Jugador(nombre2, crearClase(segundoJugador));
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
    

    public void playMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        File file = new File("musica/SeleccionPj.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(0.2));
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    



}

public void stopmusic(){
    clip.stop();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        Boton botonPulsado = (Boton)e.getSource();
        String opcion=botonPulsado.getText();
        if(botonPulsado.getTipo()==""){
            ImageIcon image = new ImageIcon("gif/"+opcion+botonPulsado.getTipo()+".gif");
            Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gifear.setIcon(image);
            primerJugador=opcion;
            estadisticasPers(opcion,botonPulsado.getTipo());
        }else {
            ImageIcon image = new ImageIcon("gif/"+opcion+botonPulsado.getTipo()+".gif");
            Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gifear2.setIcon(image); 
            segundoJugador=opcion;
            estadisticasPers(opcion,botonPulsado.getTipo());
        }
        
            
        
    }
    public void estadisticasPers(String personaje,String tipo){
        String archivo="estadisticas.txt";
        try
        {
           
            Scanner scanner = new Scanner(new File(archivo));
            while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String []estadisticasPJ=linea.split(":");
            if(estadisticasPJ[0].equals(personaje) && tipo==""){

               
                try {
                    estadisticas.setText("<html><body>Vida: "+estadisticasPJ[1]+"<br>Fuerza: "+estadisticasPJ[2]+"<br>Armadura: "+estadisticasPJ[3]+"<br>Ataque especial:<br>"+estadisticasPJ[4]+"</body></html>");
                    estadisticas.setFont(font.deriveFont(Font.BOLD,25));
                    estadisticas.setForeground(Color.WHITE);
                } catch (Exception e) {
                    //TODO: handle exception
                }
               
            }else if(estadisticasPJ[0].equals(personaje) && tipo!=""){
                estadisticas2.setText("<html><body>Vida: "+estadisticasPJ[1]+"<br>Fuerza: "+estadisticasPJ[2]+"<br>Armadura:<br> "+estadisticasPJ[3]+"<br>Ataque especial:<br>"+estadisticasPJ[4]+" </body></html>");
                try {
                    estadisticas2.setFont(font.deriveFont(Font.BOLD,25));
                    estadisticas2.setForeground(Color.WHITE);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                
            }

            }
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    
}    
