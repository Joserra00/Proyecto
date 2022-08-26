import java.util.Random;

public class Jin extends Clase{
    Random r = new Random();
    
    public Jin(){
        this.armor=60;
        this.energy=20;
        this.health=100;
        this.strength=40;
        this.nombre="JIN KAZAMA";
    }
    @Override
    public int ataqueEspecial() {
        return r.nextInt(24);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public int ataqueCritico() {
        return (r.nextInt(16))*2;
    }

    @Override
    public int ataqueBasico() {
        return r.nextInt(16);
    }
    
}
