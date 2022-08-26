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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Inicio extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton iniciar;
    private JLabel fondo, titulo, jugador1text, jugador2text;
    private JTextField jugador1, jugador2;
    private Clip clip;

    public Inicio() {
        try {
            playMusic();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        jugador1 = new JTextField("Jugador1");
        jugador2 = new JTextField("Jugador2");
        jugador1text = new JLabel("Jugador1");
        jugador2text = new JLabel("Jugador2");
        jugador1.setBounds(280, 400, 160, 50);
        jugador2.setBounds(1150, 400, 160, 50);
        jugador1text.setBounds(280, 350, 160, 50);
        jugador2text.setBounds(1150, 350, 160, 50);
        panel = new JPanel();
        iniciar = new JButton("Iniciar");
        panel.add(iniciar);
        this.setTitle("Pantalla de Inicio");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        this.setSize(1600, 900);
        this.setVisible(true);
        this.add(panel);
        titulo = new JLabel("FIGHTER I");
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("fuente/Odachi.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            this.setFont(font.deriveFont(Font.BOLD));
            titulo.setFont(font.deriveFont(Font.BOLD, 180));
            titulo.setForeground(Color.WHITE);
            jugador1text.setForeground(Color.WHITE);
            jugador2text.setForeground(Color.WHITE);
            iniciar.setFont(font.deriveFont(Font.BOLD, 40));
            iniciar.setFont(font.deriveFont(Font.BOLD, 40));
            iniciar.setBorder(new LineBorder(Color.GRAY, 3));
            iniciar.setBackground(Color.WHITE);
            iniciar.setForeground(Color.BLACK);
            iniciar.setOpaque(true);
            jugador1.setFont(font.deriveFont(Font.BOLD, 25));
            jugador2.setFont(font.deriveFont(Font.BOLD, 25));
            jugador1text.setFont(font.deriveFont(Font.BOLD, 40));
            jugador2text.setFont(font.deriveFont(Font.BOLD, 40));
        } catch (FontFormatException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        this.setResizable(false);

        iniciar.setBounds(1600 / 2 - 90, 900 / 2 + 100, 200, 80);
        panel.add(jugador1);
        panel.add(jugador2);
        panel.add(jugador1text);
        panel.add(jugador2text);
        panel.add(iniciar);
        panel.add(titulo);
        titulo.setBounds(1600 / 2 - 300, 0, 600, 250);
        ImageIcon imagen = new ImageIcon("gif/Inicio.gif");
        Image imagen2 = imagen.getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT);
        imagen = new ImageIcon(imagen2);
        fondo = new JLabel();
        fondo.setIcon(imagen);
        panel.add(fondo);
        fondo.setBounds(0, 0, 1600, 900);
        iniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopmusic();
                dispose();
                String nombre1 = jugador1.getText();
                String nombre2 = jugador2.getText();
                new SeleccionPj(nombre1, nombre2);

            }

        });

    }

    public void playMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("musica/mainmenu.wav");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) throws MalformedURLException {
        new Inicio();
    }

}
