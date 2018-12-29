package POJOs;



import entidades.Libro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Busqueda implements Serializable {
    private List<Libro> libros=new ArrayList();
    private String libroBusqueda;
    private String tipoBusqueda;
    
    public Busqueda(){
        libros=new ArrayList();
        libroBusqueda="";
        tipoBusqueda="";
    }
    
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getLibroBusqueda() {
        return libroBusqueda;
    }

    public void setLibroBusqueda(String libroBusqueda) {
        this.libroBusqueda = libroBusqueda;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }
     
   
}
