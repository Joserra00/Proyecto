public class Jugador{
    protected String nombre;
    protected TipoClase clase;
    public Jugador(String nombre, TipoClase clase){
        this.nombre = nombre;
        this.clase = clase;
    }
    public TipoClase getClase() {
        return clase;
    }
    public void setClase(TipoClase clase) {
        this.clase = clase;
    }
    
}