/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package realsquad.web.menagedbean;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import realsquad.ejb.AbstractBean;
import realsquad.ejb.PlayerRequestBean;
import realsquad.entity.Player;

/**
 *
 * @author Tomek
 */
@Named("playerBean")
@SessionScoped
public class PlayerBean extends AbstractBean implements Serializable {
    @EJB
    PlayerRequestBean prb;
     private static final Logger logger = Logger.getLogger(
                "realsquad.ejb.PlayerBean");
    
    public Player getCurrentSelectedPlayer(){
        logger.info("jestem w  : getCurrentSelectedPlayer()");
        try {
            //z sessionMap biore wartosc atrybutu bookId
            String playerId = (String) context()
                                         .getExternalContext()
                                         .getSessionMap()
                                         .get("playerId");
            //wyszukuje book o wzietym Id
            
            Player player = prb.findPlayerById(playerId);
         logger.info("found player : " + player.getId());
        
        return player;
        }catch(Exception e ){
            logger.warning(
                    "Couldn't find player " + e );
            throw new EJBException(e.getMessage());
        }
    }
    
    
}
