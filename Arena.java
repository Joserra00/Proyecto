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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
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
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Arena implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private Clip clip;
    private JLabel gif1, gif2, energia, nombre, energia2, nombre2, titulo;
    private ArrayList<JLabel> atributos;
    private JButton ataquejug1, ataquejug2, ataqueespecialjug1, ataqueespecialjug2;
    private JProgressBar vidaBar, vidaBar2;

    public Arena(Jugador jug1, Jugador jug2) {
        Random r = new Random();
        frame = new JFrame();
        int n = r.nextInt(4);

        try {
            playMusic(n);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        panel = new JPanel();

        atributos = new ArrayList<JLabel>();

        energia = new JLabel("Energia: " + Integer.toString(jug1.clase.energy), SwingConstants.CENTER);
        atributos.add(energia);
        nombre = new JLabel("Nombre: " + jug1.nombre, SwingConstants.CENTER);
        atributos.add(nombre);

        energia2 = new JLabel("Energia: " + Integer.toString(jug2.clase.energy), SwingConstants.CENTER);
        atributos.add(energia2);
        nombre2 = new JLabel("Nombre: " + jug2.nombre, SwingConstants.CENTER);
        atributos.add(nombre2);
        nombre.setBounds(10, 50, 180, 50);

        energia.setBounds(10, 190, 180, 50);
        nombre2.setBounds(1400, 50, 180, 50);

        energia2.setBounds(1400, 190, 180, 50);
        ataquejug1 = new JButton("Ataque");
        ataquejug2 = new JButton("Ataque");
        ataqueespecialjug1 = new JButton("Ataque Especial");
        ataqueespecialjug2 = new JButton("Ataque Especial");
        ataquejug1.setBounds(110, 320, 180, 50);
        panel.add(ataquejug1);
        ataqueespecialjug1.setBounds(300, 320, 180, 50);
        panel.add(ataqueespecialjug1);
        ataquejug2.setBounds(1110, 320, 180, 50);
        panel.add(ataquejug2);
        ataqueespecialjug2.setBounds(1300, 320, 180, 50);
        panel.add(ataqueespecialjug2);
        frame.setTitle("Arena Combate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.setSize(1600, 900);
        frame.setVisible(true);
        frame.add(panel);
        gif2 = new JLabel();
        gif2.setBounds(1050, 320, 600, 600);
        gif2.setVisible(true);
        panel.add(gif2);
        gif1 = new JLabel();
        gif1.setBounds(100, 320, 600, 600);
        gif1.setVisible(true);
        panel.add(gif1);
        ataqueespecialjug1.setEnabled(false);
        ataqueespecialjug2.setEnabled(false);
        vidaBar = new JProgressBar(0, jug1.clase.health);
        vidaBar.setBounds(10, 120, 180, 50);
        vidaBar.setStringPainted(true);
        vidaBar.setValue(jug1.clase.health);
        vidaBar.setForeground(Color.RED);
        vidaBar.setBackground(Color.BLACK);
        panel.add(vidaBar);
        vidaBar2 = new JProgressBar(0, jug2.clase.health);
        vidaBar2.setBounds(1400, 120, 180, 50);
        vidaBar2.setStringPainted(true);
        vidaBar2.setValue(jug2.clase.health);
        vidaBar2.setForeground(Color.RED);
        vidaBar2.setBackground(Color.BLACK);
        panel.add(vidaBar2);

        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("fuente/Odachi.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            for (JLabel a : atributos) {

                a.setFont(font.deriveFont(Font.BOLD, 25));
                a.setForeground(Color.BLACK);
                a.setBackground(Color.WHITE);
                a.setOpaque(true);
                panel.add(a);
            }
            vidaBar2.setString("Vida :" + String.valueOf(jug2.clase.health));
            vidaBar2.setFont(font.deriveFont(Font.BOLD, 25));
            vidaBar.setString("Vida :" + String.valueOf(jug1.clase.health));
            vidaBar.setFont(font.deriveFont(Font.BOLD, 25));
            ataqueespecialjug1.setFont(font.deriveFont(Font.BOLD, 25));
            ataqueespecialjug2.setFont(font.deriveFont(Font.BOLD, 25));
            ataquejug1.setFont(font.deriveFont(Font.BOLD, 25));
            ataquejug2.setFont(font.deriveFont(Font.BOLD, 25));
            titulo = new JLabel("FIGHT!", SwingConstants.CENTER);
            titulo.setFont(font.deriveFont(Font.BOLD, 180));
            titulo.setForeground(Color.WHITE);
            titulo.setBounds(1600 / 2 - 300, 0, 600, 250);
            panel.add(titulo);

        } catch (FontFormatException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        frame.setResizable(false);
        ataquejug1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jug2.clase.health -= (int) ((jug1.clase.ataqueBasico() * 20) / jug2.clase.armor);
                jug1.clase.energy += 20;
                energia.setText("Energia: " + Integer.toString(jug1.clase.energy));
                vidaBar2.setValue(jug2.clase.health);
                vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                ataquejug2.setEnabled(true);
                if (jug2.clase.energy >= 100) {
                    ataqueespecialjug2.setEnabled(true);
                } else {
                    ataqueespecialjug2.setEnabled(false);
                }

                ataquejug1.setEnabled(false);
                ataqueespecialjug1.setEnabled(false);
                cargarMenu(jug1, jug2,ataquejug1,ataqueespecialjug1,ataquejug2,ataqueespecialjug2,frame);
               ataque(jug1.clase.nombre);
 

            }

        });
        ataquejug2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jug1.clase.health -= (int) ((jug2.clase.ataqueBasico() * 20) / jug1.clase.armor);
                jug2.clase.energy += 20;
                energia2.setText("Energia: " + Integer.toString(jug2.clase.energy));
                vidaBar.setValue(jug1.clase.health);
                vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                ataquejug1.setEnabled(true);
                if (jug1.clase.energy >= 100) {
                    ataqueespecialjug1.setEnabled(true);
                } else {
                    ataqueespecialjug1.setEnabled(false);
                }
                ataquejug2.setEnabled(false);
                ataqueespecialjug2.setEnabled(false);
                cargarMenu(jug1, jug2,ataquejug1,ataquejug2,ataqueespecialjug1,ataqueespecialjug2,frame);
                ataqueReversed(jug2.clase.nombre);
            }

        });
        ataqueespecialjug1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (jug1.clase.nombre) {
                    case "JIN KAZAMA":

                        jug2.clase.health -= (int) ((jug1.clase.ataqueEspecial() * 20) / jug2.clase.armor);
                        if (jug2.clase.armor > 5) {
                            jug2.clase.armor -= 5;
                        }
                        jug1.clase.energy -= 100;
                        energia.setText("Energia: " + Integer.toString(jug1.clase.energy));
                        vidaBar2.setValue(jug2.clase.health);
                        vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                        ataquejug2.setEnabled(true);
                        if (jug2.clase.energy >= 100) {
                            ataqueespecialjug2.setEnabled(true);
                        } else {
                            ataqueespecialjug2.setEnabled(false);
                        }
                        ataquejug1.setEnabled(false);
                        ataqueespecialjug1.setEnabled(false);
                        
                        break;
                    case "M. BISON":

                        jug2.clase.health -= (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                        if ((jug1.clase.health + (int) ((jug1.clase.ataqueEspecial() * 20) / jug2.clase.armor)) > 100) {
                            jug1.clase.health = 100;
                            vidaBar.setValue(jug1.clase.health);
                            vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                        } else {
                            jug1.clase.health += (int) ((jug1.clase.ataqueEspecial() * 20) / jug2.clase.armor);
                            vidaBar.setValue(jug1.clase.health);
                            vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));

                        }
                        jug1.clase.energy -= 100;
                        energia.setText("Energia: " + Integer.toString(jug1.clase.energy));
                        vidaBar2.setValue(jug2.clase.health);
                        vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));

                        ataquejug2.setEnabled(true);
                        if (jug2.clase.energy >= 100) {
                            ataqueespecialjug2.setEnabled(true);
                        } else {
                            ataqueespecialjug2.setEnabled(false);
                        }
                        ataquejug1.setEnabled(false);
                        ataqueespecialjug1.setEnabled(false);
                        
                        break;
                    case "CHUN LI":

                        jug2.clase.health -= (int) ((jug1.clase.ataqueEspecial() * 20) / jug2.clase.armor);
                        jug1.clase.armor += 5;
                        jug1.clase.energy -= 100;
                        energia.setText("Energia: " + Integer.toString(jug1.clase.energy));
                        vidaBar2.setValue(jug2.clase.health);
                        vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                        ataquejug2.setEnabled(true);
                        if (jug2.clase.energy >= 100) {
                            ataqueespecialjug2.setEnabled(true);
                        } else {
                            ataqueespecialjug2.setEnabled(false);
                        }
                        ataquejug1.setEnabled(false);
                        ataqueespecialjug1.setEnabled(false);
                       
                        break;
                    case "ZANGIEF":

                        jug2.clase.health -= (int) ((jug1.clase.ataqueEspecial() * 20) / jug2.clase.armor);
                        jug1.clase.energy -= 100;
                        energia.setText("Energia: " + Integer.toString(jug1.clase.energy));
                        vidaBar2.setValue(jug2.clase.health);
                        vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                        ataquejug2.setEnabled(true);
                        if (jug2.clase.energy >= 100) {
                            ataqueespecialjug2.setEnabled(true);
                        } else {
                            ataqueespecialjug2.setEnabled(false);
                        }
                        ataquejug1.setEnabled(false);
                        ataqueespecialjug1.setEnabled(false);
                        
                        break;
                }
                cargarMenu(jug1, jug2,ataquejug1,ataqueespecialjug1,ataquejug2,ataqueespecialjug2,frame);
                ataqueEspecial(jug1.clase.nombre);
            }

        });
        ataqueespecialjug2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (jug2.clase.nombre) {
                    case "JIN KAZAMA":

                        jug1.clase.health -= (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                        if (jug1.clase.armor > 5) {
                            jug1.clase.armor -= 5;
                        }

                        jug2.clase.energy -= 100;
                        energia2.setText("Energia: " + Integer.toString(jug2.clase.energy));
                        vidaBar.setValue(jug1.clase.health);
                        vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                        ataquejug1.setEnabled(true);
                        if (jug1.clase.energy >= 100) {
                            ataqueespecialjug1.setEnabled(true);
                        } else {
                            ataqueespecialjug1.setEnabled(false);
                        }
                        ataquejug2.setEnabled(false);
                        ataqueespecialjug2.setEnabled(false);
                        
                        break;
                    case "M. BISON":

                        jug1.clase.health -= (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                        if ((jug2.clase.health + (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor)) > 100) {
                            jug2.clase.health = 100;
                            vidaBar2.setValue(jug2.clase.health);
                            vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                        } else {
                            jug2.clase.health += (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                            vidaBar2.setValue(jug2.clase.health);
                            vidaBar2.setString("VIDA: " + String.valueOf(jug2.clase.health));
                        }

                        jug2.clase.energy -= 100;
                        energia2.setText("Energia: " + Integer.toString(jug2.clase.energy));
                        vidaBar.setValue(jug1.clase.health);
                        vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                        ataquejug1.setEnabled(true);
                        if (jug1.clase.energy >= 100) {
                            ataqueespecialjug1.setEnabled(true);
                        } else {
                            ataqueespecialjug1.setEnabled(false);
                        }
                        ataquejug2.setEnabled(false);
                        ataqueespecialjug2.setEnabled(false);
                        
                        break;
                    case "CHUN LI":

                        jug1.clase.health -= (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                        jug1.clase.armor += 5;
                        jug2.clase.energy -= 100;
                        energia2.setText("Energia: " + Integer.toString(jug2.clase.energy));
                        vidaBar.setValue(jug1.clase.health);
                        vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                        ataquejug1.setEnabled(true);
                        if (jug1.clase.energy >= 100) {
                            ataqueespecialjug1.setEnabled(true);
                        } else {
                            ataqueespecialjug1.setEnabled(false);
                        }
                        ataquejug2.setEnabled(false);
                        ataqueespecialjug2.setEnabled(false);
                        
                        break;
                    case "ZANGIEF":

                        jug1.clase.health -= (int) ((jug2.clase.ataqueEspecial() * 20) / jug1.clase.armor);
                        jug2.clase.energy -= 100;
                        energia2.setText("Energia: " + Integer.toString(jug2.clase.energy));
                        vidaBar.setValue(jug1.clase.health);
                        vidaBar.setString("VIDA: " + String.valueOf(jug1.clase.health));
                        ataquejug1.setEnabled(true);
                        if (jug1.clase.energy >= 100) {
                            ataqueespecialjug1.setEnabled(true);
                        } else {
                            ataqueespecialjug1.setEnabled(false);
                        }
                        ataquejug2.setEnabled(false);
                        ataqueespecialjug2.setEnabled(false);
                        
                        break;
                }
                cargarMenu(jug1, jug2,ataquejug1,ataqueespecialjug1,ataquejug2,ataqueespecialjug2,frame);
                ataqueEspecialReversed(jug2.clase.nombre);
            }

        });
        ImageIcon imagen = new ImageIcon("gif/Arena" + n + ".gif");
        Image imagen2 = imagen.getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT);
        imagen = new ImageIcon(imagen2);
        JLabel fondo = new JLabel();
        fondo.setIcon(imagen);
        panel.add(fondo);
        fondo.setBounds(0, 0, 1600, 900);

        ImageIcon image = new ImageIcon("gifarena/" + jug1.clase.nombre + ".gif");
        imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif1.setIcon(image);
        image = new ImageIcon("gifarena/" + jug2.clase.nombre + "REVERSED.gif");
        imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif2.setIcon(image);

    }

    public void playMusic(int n) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        File file = new File("musica/Musica" + n + ".wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(0.3));
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stopmusic() {
        clip.stop();
    }

    public void cargarMenu(Jugador jug1, Jugador jug2,JButton ataque,JButton ataqueespecialjug1,JButton ataque2,JButton ataqueespecialjug2,JFrame frame) {
        if (jug1.clase.health <= 0) {
            ataque.setEnabled(false);
            ataque2.setEnabled(false);
            ataqueespecialjug1.setEnabled(false);
            ataqueespecialjug2.setEnabled(false);
            stopmusic();
            vidaBar.setString("VIDA: " + "0");
            
            new MenuFinPartida(jug1, jug2, jug2.nombre,frame);
           
            
            
            
        } else if (jug2.clase.health <= 0) {
            ataque.setEnabled(false);
            ataque2.setEnabled(false);
            ataqueespecialjug1.setEnabled(false);
            ataqueespecialjug2.setEnabled(false);
            stopmusic();
            vidaBar2.setString("VIDA: " + "0");
            
            new MenuFinPartida(jug1, jug2, jug1.nombre,frame);
            
            
        }
    }

    public void ataque(String personaje){
        ImageIcon image = new ImageIcon("gif/"+personaje+"ATTACK.gif");
        Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif1.setIcon(image);
        gif1.setBounds(800, 320, 600, 600);
        new Timer().schedule(
             new java.util.TimerTask() { 
                @Override public void run() {
                    gif1.setBounds(100, 320, 600, 600);
                    ImageIcon image = new ImageIcon("gif/"+personaje+".gif");
            Image imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gif1.setIcon(image);
                       } 
                    }, 1000
                    );

    }
    public void ataqueReversed(String personaje){
        ImageIcon image = new ImageIcon("gif/"+personaje+"REVERSEDATTACK.gif");
        Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif2.setIcon(image);
        gif2.setBounds(400, 320, 600, 600);
        new Timer().schedule(
             new java.util.TimerTask() { 
                @Override public void run() {
                    gif2.setBounds(1005, 320, 600, 600);
                    ImageIcon image = new ImageIcon("gif/"+personaje+"REVERSED.gif");
            Image imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gif2.setIcon(image);
                       } 
                    }, 1000
                    );

    }

    public void ataqueEspecial(String personaje){
        ImageIcon image = new ImageIcon("gif/"+personaje+"SPECIALATTACK.gif");
        Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif1.setIcon(image);
        gif1.setBounds(800, 320, 600, 600);
        new Timer().schedule(
             new java.util.TimerTask() { 
                @Override public void run() {
                    gif1.setBounds(100, 320, 600, 600);
                    ImageIcon image = new ImageIcon("gif/"+personaje+".gif");
            Image imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gif1.setIcon(image);
                       } 
                    }, 1000
                    );

    }
    public void ataqueEspecialReversed(String personaje){
        ImageIcon image = new ImageIcon("gif/"+personaje+"SPECIALREVERSEDATTACK.gif");
        Image imagen2 = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagen2);
        gif2.setIcon(image);
        gif2.setBounds(400, 320, 600, 600);
        new Timer().schedule(
             new java.util.TimerTask() { 
                @Override public void run() {
                    gif2.setBounds(1005, 320, 600, 600);
                    ImageIcon image = new ImageIcon("gif/"+personaje+"REVERSED.gif");
            Image imagen2 = image.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
            image = new ImageIcon(imagen2);
            gif2.setIcon(image);
                       } 
                    }, 1000
                    );

    }


   
    public static void main(String[] args) throws MalformedURLException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
