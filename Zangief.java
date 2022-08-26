import java.util.Random;

public class Zangief extends Clase{
    Random r = new Random();
    
    public Zangief(){
        this.health=180;
        this.armor=55;
        this.energy=20;
        this.strength=35;
        this.nombre="ZANGIEF";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int ataqueEspecial() {
        
        return ataqueBasico()*3;
    }

    @Override
    public int ataqueCritico() {
        return (r.nextInt(8))*2;
    }

    @Override
    public int ataqueBasico() {
        return r.nextInt(8);
    }
    
}
