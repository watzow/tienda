package managedBeans;

import POJOs.Busqueda;
import POJOs.CarroCompra;
import enterpriseBeans.CarroCompraEJB;
import enterpriseBeans.CatalogoEJB;
import enterpriseBeans.ClienteEJB;
import entidades.Cliente;
import entidades.Libro;
import static entidades.LibroVendido_.pedido;
import entidades.Pedido;
import entidades.Tema;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author jrvidal
 */
@Named(value = "sesionMB")
@SessionScoped
public class SesionMB implements Serializable {

    @EJB
    private ClienteEJB clienteEJB;
    private Cliente cliente;
    private String errorMessage;
    private String nombre;
    private String mail;
    private String direccion;
    private String login;
    private String password;
    private String password2;
    private String loginTime;
    @EJB
    private CatalogoEJB catalogoEJB;
    private Tema tema;
    private Libro libro;
    
    private Busqueda busqueda;
    private String libroBusqueda;
    
    @EJB
    private CarroCompraEJB carroCompraEJB;
    private CarroCompra carroCompra = new CarroCompra();
    
    
    public String login() {
        cliente = clienteEJB.login(login, password);
        if (cliente != null) {
            GregorianCalendar calendar = new GregorianCalendar();
            loginTime = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ",  "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            return "inicio";
        } else {
            return "";
        }
    }

    public String logout() {
        clienteEJB.logout();
        cliente = null;
        return "inicio";
    }
    public String editarperfil(){
        clienteEJB.editaperfil(cliente, nombre, direccion, mail, login, password, password2);
        return "inicio";
    }
    public String registra() {
        errorMessage = clienteEJB.registra(nombre, direccion, mail, login, password, password2);
        if (errorMessage.equals("none")) {
            return login();
        } else {
            return ("registroError");
        }
    }

    public boolean isLogged() {
        return cliente != null;
    }

    public String poneEnCarro(Libro libro) {
        carroCompra.ponEnCarro(libro);
        return "carroCompra";
    }

    public String vaciaCarro() {
        carroCompra.vacia();
        return "listaTemas";
    }

    public String confirmaPedido() {
        carroCompraEJB.confirmaPedido(cliente,carroCompra);
        return "listaPedidos";
    }
    public String buscar(String tipoBusqueda){
        busqueda = new Busqueda();
        busqueda.setLibroBusqueda(libroBusqueda);
        busqueda.setTipoBusqueda(tipoBusqueda);
        if(busqueda.getLibroBusqueda().equals("")){
            return "";
        }
        busqueda.setLibros(catalogoEJB.buscarLibro(libroBusqueda, tipoBusqueda));
        return "resultadosBusqueda";
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public List<Libro> getLibros() {
        return catalogoEJB.todosLosLibros();
    }
    
    public List<Tema> getTemas() {
        return catalogoEJB.todosLosTemas();
    }
    public String deletePedido(Pedido pedido){
        carroCompraEJB.eliminaPedido(cliente, pedido);
        return "listaPedidos";
    }
    public String verTema(Tema tema) {
        this.tema = tema;
        return "listaLibros";
    }

    public String verLibro(Libro libro) {
        this.libro = libro;
        return "detallesLibro";
    }

    public Cliente getCliente() {
        return cliente;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public Tema getTema() {
        return tema;
    }

    public Libro getLibro() {
        return libro;
    }

    public CarroCompra getCarroCompra() {
        return carroCompra;
    }

    public String getLibroBusqueda() {
        return libroBusqueda;
    }

    public void setLibroBusqueda(String libroBusqueda) {
        this.libroBusqueda = libroBusqueda;
    }

    public Busqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }
    
}