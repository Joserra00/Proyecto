import javax.swing.JButton;

public class Boton extends JButton {
    private String tipo;
        public Boton(String texto,String tipo){
            this.setText(texto);
            this.tipo=tipo;
            

        }
        public String getTipo() {
            return tipo;
        }
        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

    
}
