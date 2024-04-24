package Persistencia;

import Persistencia.Cliente;
import Persistencia.Zona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-20T22:17:35")
@StaticMetamodel(Boleto.class)
public class Boleto_ { 

    public static volatile SingularAttribute<Boleto, String> codboleto;
    public static volatile SingularAttribute<Boleto, Zona> codzona;
    public static volatile SingularAttribute<Boleto, Cliente> dni;
    public static volatile SingularAttribute<Boleto, String> nombres;

}