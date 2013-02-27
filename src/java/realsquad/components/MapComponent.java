/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package realsquad.components;


import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import realsquad.listeners.AreaSelectedEvent;


/**
 * <p>{@link MapComponent} is a JavaServer Faces component that corresponds
 * to a client-side image map.  It can have one or more children of type
 * {@link AreaComponent}, each representing hot spots, which a user can
 * click on and mouse over.</p>
 *
 * <p>This component is a source of {@link AreaSelectedEvent} events,
 * which are fired whenever the current area is changed.</p>
 */
/*MapComponent has one or more AreaComponent instances as children. Its behavior consists of
the following actions:
■ Retrieving the value of the currently selected area
■ Defining the properties corresponding to the component's values
■ Generating an event when the user clicks on the image map
■ Queuing the event
■ Saving its state
■ Rendering the HTML map tag and the HTML input tag
MapComponent delegates the rendering of the HTML map and input tags to the MapRenderer
class.*/




//The MapComponent class extends UICommand and therefore implements
//ActionSource2, which means it can fire action events when a user clicks on the map
@FacesComponent("DemoMap")
public class MapComponent extends UICommand {
    private String current = null;

    public MapComponent() {
        super();
    }

    /**
     * <p>Return the alternate text label for the currently selected
     * child {@link AreaComponent}.</p>
     */
    public String getCurrent() {
        return (this.current);
    }

    /**
     * <p>Set the alternate text label for the currently selected child.
     * If this is different from the previous value, fire an
     * {@link AreaSelectedEvent} to interested listeners.</p>
     *
     * @param current The new alternate text label
     */
    //wywolywany przez MapRenderer.decode() 
    //current - This value represents the currently selected area.
    public void setCurrent(String current) {
        if (this.getParent() == null) {
            return;
        }

        String previous = this.current;
        this.current = current;

        // Fire an {@link AreaSelectedEvent} if appropriate
        if ((previous == null) && (current == null)) {
            // do nothing
        } else if ((previous != null)
                && (current != null)
                && (previous.equals(current))) {
            // do nothing
        } else {
            this.queueEvent(new AreaSelectedEvent(this));
        }
    }

    /**
     * 
     * <p>Return the component family for this component.</p>
     * which is used to look up renderers that can render the component.
     */
    /*If your custom component class delegates rendering, it needs to override the getFamily
method of UIComponent to return the identifier of a component family, which is used to refer to
a component or set of components that can be rendered by a renderer or set of renderers. The
component family is used along with the renderer type to look up renderers that can render the
component:*/
    
    @Override
    public String getFamily() {
        return ("Map");
    }

    /**
     * <p>Return the state to be saved for this component.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    /*Because component classes implement StateHolder, they must implement the
saveState(FacesContext) and restoreState(FacesContext, Object) methods to help the
JavaServer Faces implementation save and restore the state of components across multiple
requests.*/
    
    //To save a set of values, you must implement the saveState(FacesContext) method. This
//method is called during the Render Response phase, during which the state of the response is
//saved for processing on subsequent requests.Here is the method:
    @Override
    //This method initializes an array, which will hold the saved state. It next saves all of the state
   //associated with the component.
    public Object saveState(FacesContext context) {
        Object[] values = new Object[2];
        values[0] = super.saveState(context);
        values[1] = current;

        return (values);
    }

    /**
     * <p>Restore the state for this component.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param state   State to be restored
     *
     * @throws IOException if an input/output error occurs
     */
    @Override
    /*A component that implements StateHolder must also provide an implementation for
restoreState(FacesContext, Object), which restores the state of the component to that
saved with the saveState(FacesContext) method. The restoreState(FacesContext,
Object) method is called during the Restore View phase, during which the JavaServer Faces
implementation checks whether there is any state that was saved during the last Render
Response phase and needs to be restored in preparation for the next postback.Here is the
restoreState(FacesContext, Object) method*/
    public void restoreState(
        FacesContext context,
        Object state) {//This method takes a FacesContext and an Object instance, representing the array that is
//holding the state for the component. This method sets the component’s properties to the values
//saved in the Object array.
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        current = (String) values[1];
    }
    /*When you implement these methods in your component class, be sure to specify in the
deployment descriptor where you want the state to be saved: either client or server. If state is
saved on the client, the state of the entire view is rendered to a hidden field on the page.
To specify where state is saved for a particular web application, you need to set the
javax.faces.STATE_SAVING_METHOD context parameter to either client or server in your
application’s deployment descriptor. For the Duke's Bookstore case study, the state is saved on
the server.*/
}
