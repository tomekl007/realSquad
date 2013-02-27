/*The ActionEvent indicating that an AreaComponent from the MapComponent has been
selected.
 */


package realsquad.listeners;


import javax.faces.event.ActionEvent;
import realsquad.components.MapComponent;


/**
 * <p>An {@link ActionEvent} indicating that the specified {@link AreaComponent}
 * has just become the currently selected hotspot within the source
 * {@link MapComponent}.</p>
 */
/*In addition to the method that processes the event/MapBookChangeListener.processEvent(), 
 * you need the event class itself. This class is
very simple to write: You have it extend ActionEvent 
* and provide a constructor that takes the
component on which the event is queued and a method 
* that returns the component.Here is the
AreaSelectedEvent class used with the image map:*/

public class AreaSelectedEvent extends ActionEvent {
    /**
     * <p>Construct a new {@link AreaSelectedEvent} from the specified
     * source map.</p>
     *
     * @param map The {@link MapComponent} originating this event
     */
    public AreaSelectedEvent(MapComponent map) {
        super(map);
    }

    /**
     * <p>Return the {@link MapComponent} of the map for which an area
     * was selected.</p>
     */
    public MapComponent getMapComponent() {
        return ((MapComponent) getComponent());
    }
}
