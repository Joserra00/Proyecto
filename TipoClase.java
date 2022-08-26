
public abstract class TipoClase {
    protected int strength;
    protected int armor;
    protected int health;
    protected int energy;
    protected String nombre;

    public TipoClase() {
        this.strength = 0;
        this.armor = 0;
        this.health = 0;
        this.energy = 0;
        this.nombre="";
    }

    public abstract int ataqueEspecial();

    public abstract int ataqueCritico();

    public abstract int ataqueBasico();

}
