import java.util.Random;

public class Bison extends Clase{
    Random r = new Random();
   

    public Bison() {
        this.health=100;
        this.strength=70;
        this.energy=20;
        this.armor=40;
        this.nombre="M. BISON";
        
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
    public int ataqueCritico() {
        return (r.nextInt(28))*2;
    }

    @Override
    public int ataqueBasico() {
        return r.nextInt(28);
    }
    
}
