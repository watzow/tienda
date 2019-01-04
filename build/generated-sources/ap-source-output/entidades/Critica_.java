package entidades;

import entidades.Cliente;
import entidades.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T17:53:03")
@StaticMetamodel(Critica.class)
public class Critica_ { 

    public static volatile SingularAttribute<Critica, String> texto;
    public static volatile SingularAttribute<Critica, Libro> libro;
    public static volatile SingularAttribute<Critica, Cliente> cliente;
    public static volatile SingularAttribute<Critica, Long> id;

}