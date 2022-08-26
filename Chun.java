import java.util.Random;

public class Chun extends Clase{
    Random r = new Random();
    
    public Chun() {
        this.armor=20;
        this.health=100;
        this.strength=80;
        this.energy=20;
        this.nombre="CHUN LI";
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int ataqueEspecial() {
        return r.nextInt(36);
    }

    @Override
    public int ataqueBasico() {
        return r.nextInt(36);
    }

    @Override
    public int ataqueCritico() {
        return (r.nextInt(36))*2;
    }
    
}
