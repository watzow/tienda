package POJOs;

import entidades.Libro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jrvidal
 */
public class CarroCompra implements Serializable{

    private List<LibroEnCarro> librosEnCarro = new ArrayList<>();

    public void ponEnCarro(Libro libro) {
        for (LibroEnCarro libroEnCarro : librosEnCarro) {
            if (libroEnCarro.getLibro().equals(libro)) {
                libroEnCarro.incCantidad();
                return;
            }
        }
        librosEnCarro.add(new LibroEnCarro(libro));
    }

    public void vacia() {
        librosEnCarro.clear();
    }
    public void eliminar(LibroEnCarro libroEC){
        for(int i=0; i<librosEnCarro.size(); i++){
            if(librosEnCarro.get(i).equals(libroEC)){
                librosEnCarro.remove(i);
                i=librosEnCarro.size();
            }
        }
    }
    public boolean isVacio() {
        return librosEnCarro.isEmpty();
    }

    public double getTotal() {
        double total = 0;
        for (LibroEnCarro libroEnCarro : librosEnCarro) {
            total += libroEnCarro.getSubTotal();
        }
        return (total);
    }

    public List<LibroEnCarro> getLibrosEnCarro() {
        return librosEnCarro;
    }

    public int getNumLibrosEnCarro() {
        return librosEnCarro.size();
    }
}
