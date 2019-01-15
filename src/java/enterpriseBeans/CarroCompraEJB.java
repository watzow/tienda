package enterpriseBeans;

import POJOs.CarroCompra;
import POJOs.LibroEnCarro;
import entidades.Cliente;
import entidades.Libro;
import entidades.LibroVendido;
import entidades.Pedido;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jrvidal
 */
@PermitAll      // Ñapa hecha al codigo. Funcionar funciona, de momento
@Stateless
public class CarroCompraEJB {
    @PersistenceContext(unitName = "tiendaPU")
    private EntityManager em;

    //@RolesAllowed("clientes")
    public void confirmaPedido(Cliente cliente, CarroCompra carroCompra) {
        Pedido pedido = new Pedido();
        for (LibroEnCarro libroEnCarro : carroCompra.getLibrosEnCarro()) {
            Libro libro = libroEnCarro.getLibro();
            libro.decInventario(Math.min(libro.getInventario(), libroEnCarro.getCantidad()));
            em.merge(libro);
            LibroVendido libroVendido = new LibroVendido();
            libroVendido.setLibro(libro);
            libroVendido.setCantidad(libroEnCarro.getCantidad());
            libroVendido.setImporte(libroEnCarro.getSubTotal());
            libroVendido.setPedido(pedido);
            pedido.addLibro(libroVendido);
            cliente.addLibro(libroVendido);
        }
        pedido.setImporte(carroCompra.getTotal());
        pedido.setEstado("pendiente");
        pedido.setCliente(cliente);

        GregorianCalendar calendar = new GregorianCalendar();
        pedido.setFecha(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ",  "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        cliente.getPedidos().add(pedido);
        em.merge(cliente);
        carroCompra.vacia();
    }
    public void eliminaPedido(Cliente cliente, Pedido pedido) {
        
        for (LibroVendido librovendido : pedido.getLibros()){
            Libro libro = librovendido.getLibro();
            libro.incInventario(librovendido.getCantidad());
            em.merge(libro);
                       
        }    
        cliente.getPedidos().remove(pedido);      
        em.merge(cliente);
    }
}
