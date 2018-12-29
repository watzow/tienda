package POJOs;

import entidades.Libro;
import java.io.Serializable;

/**
 *
 * @author jrvidal
 */
public class LibroEnCarro implements Serializable{

    private Libro libro;
    private int cantidad;
    private float subTotal;

    public LibroEnCarro(Libro libro) {
        this.libro = libro;
        cantidad = 1;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incCantidad() {
        cantidad++;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubTotal() {
        subTotal = cantidad * libro.getPrecio();
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
