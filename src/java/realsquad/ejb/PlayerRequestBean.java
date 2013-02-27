/*
Two enterprise beans located in the dukesbookstore.ejb package provide the business logic
for Duke's Bookstore.
PlayerRequestBean is a stateless session bean that contains the business methods for the
application. The methods create, retrieve, and purchase books, and update the inventory for a
book. To retrieve the books, the getBooks method calls the findBooks named query defined in
the Book entity.
 */


package realsquad.ejb;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import realsquad.entity.Player;
import realsquad.util.Position;


/**
 * <p>Stateless session bean for the bookstore example.</p>
 */
@Stateless
@Named
public class PlayerRequestBean {
    private static final Logger logger = Logger.getLogger(
                "dukesbookstore.ejb.BookRequestBean");
    @PersistenceContext
    private EntityManager em;
    private final String real = "realmadrid";

    
    public PlayerRequestBean() throws Exception {
    }
    
     public void createPlayer(
        String name,
        String surname,
        Position position,
        String id
        ) {
        try {
            Player player = new Player(name,surname, position,id);
            logger.log(Level.INFO, "Created player {0}", player.getId() );
            em.persist(player);
            logger.log(Level.INFO, "Persisted book {0}", player.getId());
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

     public Player findPlayerById(String playerId){
         logger.info("jestem w  : findPlayerById");
        try {
            Player player = em.find(Player.class, playerId);
            logger.info("Found playerId " + playerId);
            return player;
            
        } catch (Exception e) {
            logger.warning(
                    "Couldn't find movie by playerId " + playerId
                    + ".");
            throw new EJBException(e.getMessage());
        }
        
    }
     
     public String getReal(){
         return real;
     }
}