package enterpriseBeans;

import entidades.Libro;
import entidades.Tema;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jrvidal
 */
@Stateless
public class CatalogoEJB {
    
    @PersistenceContext(unitName = "tiendaPU")
    private EntityManager em;

    public List<Tema> todosLosTemas() {
        TypedQuery<Tema> query = em.createQuery("SELECT t FROM Tema t", Tema.class);
        return query.getResultList();
    }
    public List<Libro> todosLosLibros(){
        TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l", Libro.class);
        return query.getResultList();
    }
    public List<Libro> buscarLibro(String libroBusqueda, String tipoBusqueda){
        if(tipoBusqueda.equals("titulo")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE '%" + libroBusqueda + "%' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        if(tipoBusqueda.equals("autor")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.autor LIKE '%" + libroBusqueda + "%' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        if(tipoBusqueda.equals("tema")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.tema.nombre LIKE '%" + libroBusqueda + "%' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        if(tipoBusqueda.equals("anyo")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.anyo='" + libroBusqueda + "' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        if(tipoBusqueda.equals("editorial")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.editorial LIKE '%" + libroBusqueda + "%' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        if(tipoBusqueda.equals("ISBN")){
            TypedQuery<Libro> query= em.createQuery("SELECT l FROM Libro l WHERE l.ISBN LIKE '%" + libroBusqueda + "%' ORDER BY l.titulo", Libro.class);
            return query.getResultList();
        }
        return new ArrayList<Libro>(); 
    }
}