/*
 ConfigBean is a singleton session bean used to create the books in the catalog when the
application is initially deployed. It calls the createBook method defined in PlayerRequestBean
 */


package realsquad.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import realsquad.util.Position;


/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton
@Startup
public class ConfigBean {
    @EJB
    private PlayerRequestBean request;

    @PostConstruct
    public void createData() {
        request.createPlayer("Cristiano", "Ronaldo", Position.LW, "7");//id is number of Player
    }
}
