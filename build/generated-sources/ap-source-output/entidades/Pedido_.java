package entidades;

import entidades.Cliente;
import entidades.LibroVendido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T17:53:03")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile ListAttribute<Pedido, LibroVendido> libros;
    public static volatile SingularAttribute<Pedido, Cliente> cliente;
    public static volatile SingularAttribute<Pedido, String> fecha;
    public static volatile SingularAttribute<Pedido, String> estado;
    public static volatile SingularAttribute<Pedido, Long> id;
    public static volatile SingularAttribute<Pedido, Double> importe;

}