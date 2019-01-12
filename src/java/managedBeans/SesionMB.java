package managedBeans;

import POJOs.Busqueda;
import POJOs.CarroCompra;
import enterpriseBeans.CarroCompraEJB;
import enterpriseBeans.CatalogoEJB;
import enterpriseBeans.ClienteEJB;
import entidades.Cliente;
import entidades.Critica;
import entidades.Libro;
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
    
    private String nombre_edit;
    private String mail_edit;
    private String direccion_edit;
    private String password_edit;
    private String password2_edit;
    
    private String textocritica;
    
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
    public String registra() {
        errorMessage = clienteEJB.registra(nombre, direccion, mail, login, password, password2);
        if (errorMessage.equals("none")) {
            return login();
        } else {
            return ("registroError");
        }
    }
    public String edita(){
        Cliente c=clienteEJB.editaperfil(cliente, nombre_edit, direccion_edit, mail_edit, password_edit, password2_edit);
        if(c!=null){
            cliente=c;
            nombre= nombre_edit;
            mail= mail_edit;
            direccion= direccion_edit;
            password= password_edit;
            password2= password2_edit;
            return "edicionCorrecta";
        }
        return "edicionIncorrecta";
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
    
    public boolean librocomprado(){
        return clienteEJB.checkVendido(libro);
    }
    
    public String critica(){
        
        if (!clienteEJB.checkVendido(libro)){
            return "criticaIncorrecta";
        }
        if(textocritica.equals("") || textocritica==null){
            return "";
        }
        clienteEJB.comentar(cliente, libro, textocritica);
        return "detallesLibro";
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
    public String getNombre_edit() {
        return nombre_edit;
    }


    public void setNombre_edit(String nombre_edit) {
        this.nombre_edit = nombre_edit;
    }
    public String getPassword_edit() {
        return password_edit;
    }
  

    public void setPassword_edit(String password_edit) {
        this.password_edit = password_edit;
    }

    public String getPassword2_edit() {
        return password2_edit;
    }

    public void setPassword2_edit(String password2_edit) {
        this.password2_edit = password2_edit;
    }

    public String getMail_edit() {
        return mail_edit;
    }

    public void setMail_edit(String mail_edit) {
        this.mail_edit = mail_edit;
    }

    public String getDireccion_edit() {
        return direccion_edit;
    }

    public void setDireccion_edit(String direccion_edit) {
        this.direccion_edit = direccion_edit;
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

    public String getTextocritica() {
        return textocritica;
    }

    public void setTextocritica(String textocritica) {
        this.textocritica = textocritica;
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
