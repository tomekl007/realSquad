package realsquad.entity;

import java.util.Collection;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import realsquad.util.Position;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-07T12:18:57")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile SingularAttribute<Player, String> id;
    public static volatile SingularAttribute<Player, Position> primaryPosition;
    public static volatile SingularAttribute<Player, Collection> alternativePosition;
    public static volatile SingularAttribute<Player, String> name;
    public static volatile SingularAttribute<Player, String> surname;

}