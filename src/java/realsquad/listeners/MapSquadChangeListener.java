/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package realsquad.listeners;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author Tomek
 */
public class MapSquadChangeListener  implements ActionListener {
    private static final Logger logger = Logger.getLogger(
                "dukesbookstore.listeners.MapBookChangeListener");
    private HashMap<String, String> players = null;

    public MapSquadChangeListener() {
       players = new HashMap<String, String>(6);

        String player1 = players.put("Ronaldo", "7");
        String player2 = players.put("C.Ronaldo", "202");
        String player3 = players.put("Masterson", "203");
        String player4 = players.put("Novation", "205");
        String player5 = players.put("Thrilled", "206");
        String player6 = players.put("Coding", "207");
        //......
    }

    
    @Override
    public void processAction(ActionEvent actionEvent)
        throws AbortProcessingException {
        logger.log(Level.INFO, "Entering MapBookChangeListener.processAction");

        AreaSelectedEvent event = (AreaSelectedEvent) actionEvent;
        String current = event.getMapComponent()
                              .getCurrent();
        logger.log(Level.INFO, "current is {0}", current);

        FacesContext context = FacesContext.getCurrentInstance();
        String playerId = players.get(current);
        logger.log(Level.INFO, "playerId is {0}", playerId);
        context.getExternalContext()
               .getSessionMap()
               .put("playerId", playerId);//Return a mutable Map representing the session scope attributes for the current application
        //do tej mapy ktora reprezentuje wszystkie attrybuty dla danej sesji dodaje atrybut bookId o wartosc playerId(jest to id player 
        //na ktora kliknalem w index.xhtml
    }
    
}
