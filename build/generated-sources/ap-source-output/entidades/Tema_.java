package entidades;

import entidades.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-02T16:09:49")
@StaticMetamodel(Tema.class)
public class Tema_ { 

    public static volatile ListAttribute<Tema, Libro> libros;
    public static volatile SingularAttribute<Tema, Long> id;
    public static volatile SingularAttribute<Tema, String> nombre;

}