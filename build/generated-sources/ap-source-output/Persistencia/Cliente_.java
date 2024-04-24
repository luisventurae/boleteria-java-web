package Persistencia;

import Persistencia.Boleto;
import Persistencia.Recibo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-20T22:17:35")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile ListAttribute<Cliente, Recibo> reciboList;
    public static volatile SingularAttribute<Cliente, String> dir;
    public static volatile SingularAttribute<Cliente, String> nom;
    public static volatile SingularAttribute<Cliente, String> dni;
    public static volatile ListAttribute<Cliente, Boleto> boletoList;

}