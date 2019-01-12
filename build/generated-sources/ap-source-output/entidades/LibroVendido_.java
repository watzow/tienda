package entidades;

import entidades.Libro;
import entidades.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-11T19:02:52")
@StaticMetamodel(LibroVendido.class)
public class LibroVendido_ { 

    public static volatile SingularAttribute<LibroVendido, Libro> libro;
    public static volatile SingularAttribute<LibroVendido, Pedido> pedido;
    public static volatile SingularAttribute<LibroVendido, Long> id;
    public static volatile SingularAttribute<LibroVendido, Integer> cantidad;
    public static volatile SingularAttribute<LibroVendido, Float> importe;

}