package enterpriseBeans;

import entidades.Cliente;
import entidades.Grupo;
import entidades.Libro;
import entidades.Critica;
import entidades.LibroVendido;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jrvidal
 */
@Stateless
public class ClienteEJB {

    @PersistenceContext(unitName = "tiendaPU")
    private EntityManager em;

    public Cliente login(String login, String password) {
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.login='" + login + "'", Cliente.class);
            Cliente cliente = query.getSingleResult();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(login, password);
            return cliente;
        } catch (ServletException e) {
            return null;
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
        }
    }
    private boolean checkMail(String mail){
        TypedQuery<Cliente> tquery= em.createQuery("SELECT c FROM Cliente c WHERE c.mail='" + mail + "'", Cliente.class);
        return tquery.getResultList().isEmpty() && mail.indexOf(" ")==-1;
    }
    public Cliente editaperfil(Cliente cliente, String nombre, String direccion, String mail, String password, String password2) {
        if(!checkMail(mail) || !password.equals(password2)){
            return null;
        }
        if (!nombre.equals("") && nombre!=null) {                
            cliente.setNombre(nombre);
        }
        if (!direccion.equals("") && direccion!=null){
            cliente.setDireccion(direccion);
        }
        if (!mail.equals("") && mail!=null){
            cliente.setMail(mail);
        }    
        if (!password.equals("") && password!=null){
            cliente.setPwd(DigestUtils.sha512Hex(password));
        }
        em.merge(cliente);
        return cliente;
    }    
    
    //public boolean checkVendido(Libro libro, Cliente cliente){
  
        //TypedQuery<LibroVendido> query1 = em.createQuery("SELECT c FROM LibroVendido c ", LibroVendido.class);
        //int indice= query1.getResultList().indexOf(libro);
        //return indice!=-1;
    //}
    
    public void comentar(Cliente cliente, Libro libro, String texto){
        Critica c= new Critica();
        c.setCliente(cliente);
        c.setLibro(libro);
        c.setTexto(texto);
        libro.addCritica(c);
        cliente.addCritica(c);
        em.persist(c);
        em.merge(libro);
        em.merge(cliente);
        
    }
    
   
    public String registra(String nombre, String direccion, String mail, String login, String password, String password2) {
        if (nombre.isEmpty()) {
            return "El nombre no puede estar en blanco";
        } else if (direccion.isEmpty()) {
            return "La dirección no puede estar en blanco";
        } else if (mail.isEmpty()) {
            return "La dirección de correo no puede estar en blanco";
        } else if (login.isEmpty()) {
            return "El login no puede estar en blanco";
        } else if ((password.isEmpty()) || (!password.equals(password2))) {
            return "Las dos contraseñas introducidas no coinciden";
        } else {
            TypedQuery<Cliente> query1 = em.createQuery("SELECT c FROM Cliente c WHERE c.login='" + login + "'", Cliente.class);
            if (query1.getResultList().isEmpty()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setLogin(login);
                cliente.setMail(mail);
                cliente.setPwd(DigestUtils.sha512Hex(password));
                TypedQuery<Grupo> query2 = em.createQuery("SELECT g FROM Grupo g WHERE g.nombre= 'clientes'", Grupo.class);
                Grupo grupo = query2.getSingleResult();
                cliente.getGrupos().add(grupo);
                em.persist(cliente);
                return "none";
            } else {
                return "El login " + login + " ya existe";
            }
        }
    }
    
}