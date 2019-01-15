package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jrvidal
 */
@Entity
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String ISBN;
    private String descripcion;
    private String editorial;
    private String autor;
    @ManyToOne
    private Tema tema;
    private boolean disponible;
    private float precio;
    private int anyo;
    private int inventario;
    @OneToMany
    private List<Critica> criticas = new ArrayList<>();
    @Column(nullable = true)
    private String foto;

    
    
    public List<Critica> getCriticas() {
        return criticas;
    }

    
    public void setCriticas(List<Critica> criticas) {
        this.criticas = criticas;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public float getPrecio() {
        return precio;
    }
    public void addCritica(Critica c){
        criticas.add(c);
        
    }  
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public int getInventario() {
        return inventario;
    }

    public void decInventario(int cantidad) {
        inventario -= cantidad;
        disponible = inventario > 0;
    }
    
    public void incInventario(int cantidad) {
        inventario += cantidad;
        disponible = inventario > 0;
    }
    
    public void setInventario(int inventario) {
        this.inventario = inventario;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    public boolean isHayCriticas() {
        return !criticas.isEmpty();
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Libro[ id=" + id + " ]";
    }
}
