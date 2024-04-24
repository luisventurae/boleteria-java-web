package Persistencia;

import Persistencia.Cliente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-20T22:17:35")
@StaticMetamodel(Recibo.class)
public class Recibo_ { 

    public static volatile SingularAttribute<Recibo, String> codrecibo;
    public static volatile SingularAttribute<Recibo, Date> fecha;
    public static volatile SingularAttribute<Recibo, String> codpago;
    public static volatile SingularAttribute<Recibo, Cliente> dni;

}