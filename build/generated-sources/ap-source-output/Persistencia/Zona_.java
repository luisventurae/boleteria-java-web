package Persistencia;

import Persistencia.Boleto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-20T22:17:35")
@StaticMetamodel(Zona.class)
public class Zona_ { 

    public static volatile SingularAttribute<Zona, Double> precio;
    public static volatile SingularAttribute<Zona, String> nomzona;
    public static volatile SingularAttribute<Zona, Integer> catidad;
    public static volatile SingularAttribute<Zona, String> codzona;
    public static volatile ListAttribute<Zona, Boleto> boletoList;

}