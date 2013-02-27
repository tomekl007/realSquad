/*
 During the Render Response phase, the JavaServer Faces implementation processes the
encoding methods of all components and their associated renderers in the view. The encoding
methods convert the current local value of the component into the corresponding markup that
represents it in the response.
* 
* Because MapComponent is a parent component of AreaComponent, the area tags must be
rendered after the beginning map tag and before the ending map tag. To accomplish this, the
MapRenderer class renders the beginning map tag in encodeBegin and the rest of the map tag in
encodeEnd.
* 
* During the Apply Request Values phase, the JavaServer Faces implementation processes the
decode methods of all components in the tree. The decode method extracts a component’s local
value from incoming request parameters and uses a Converter class to convert the value to a
type that is acceptable to the component class.
 */


package realsquad.renderers;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import java.io.IOException;
import javax.faces.render.FacesRenderer;
import realsquad.components.MapComponent;


/**
 * <p>Renderer for {@link MapComponent} in an HTML environment.</p>
 * 2. Delegate rendering to a renderer if your component does not handle the rendering. To do
      this:
 */
/*The JavaServer Faces implementation automatically invokes the encodeEnd method of
AreaComponent's renderer after it invokes MapRenderer's encodeBegin method and before it
invokes MapRenderer's encodeEnd method.*/

@FacesRenderer(componentFamily = "Map", rendererType = "DemoMap")
//a.Create a custom renderer class by extending javax.faces.render.Renderer.
public class MapRenderer extends Renderer {
    public MapRenderer() {
    }

    /**
     * <p>Decode the incoming request parameters to determine which hotspot (if
     * any) has been selected.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    /*A custom component class or its renderer must implement the decode method only if it must
     retrieve the local value or if it needs to queue events. The component queues the event by calling
     queueEvent.*/
    @Override
    public void decode(
        FacesContext context,
        UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;

        //The decode method first gets the name of the hidden input field(<input type="hidden" name="bookMap_current" />) by calling
        //getName(FacesContext, UIComponent).
        String key = getName(context, map); //dostaje bookMap_current
        //It then uses that name as the key to the request parameter map to retrieve the current value of the input field.
        System.out.println("key = " + key);
        String value = (String) context.getExternalContext()
                                       .getRequestParameterMap()//Return an immutable Map whose keys are the set of request parameters names included in the current request,
                                             //and whose values (of type String) are the first (or only) value for each parameter name returned by the underlying request.
                                       .get(key);//Returns the value to which the specified key is mapped
                  //This value represents the currently selected area.
        //ex. ['bookMap_current'].value='Jeeves';

        if (value != null) {
            map.setCurrent(value);//Finally, it sets the value of the MapComponent class's 
            //current attribute to the value of the input field.
        }
    }
    
    
    
    //The encoding methods accept a UIComponent argument and a FacesContext argument. The
    //FacesContext instance contains all the information associated with the current request. The
    //UIComponent argument is the component that needs to be rendered.
    /**
     * <p>Encode the beginning of this component.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    //Notice that encodeBegin renders only the beginning map tag.
    @Override
    public void encodeBegin(
        FacesContext context,
        UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("map", map);//Write the start of an element, up to and including the element name.  
        //Once this method has been called, clients can call the writeAttribute() or writeURIAttribute() 
        //methods to add attributes and corresponding values
        
        writer.writeAttribute( //bedzie w kodzie html <map name="bookMap"
            "name",
            map.getId(), //Return the component identifier of this UIComponent.
            "id"); //Write an attribute name and corresponding value, after converting that 
        //text to a String (if necessary), and after performing any escaping 
        //appropriate for the markup language being rendered. 
      //This method may only be called after a call to startElement(), and before the opened element has been closed.
    }

    /**
     * <p>Encode the children of this component.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    @Override
    public void encodeChildren(
        FacesContext context,
        UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
    }

    /**
     * <p>Encode the ending of this component.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    //encodeEnd method renders the input tag and the ending map tag.
    //w html : <input type="hidden" name="bookMap_current" /></map>
    @Override
    public void encodeEnd(
        FacesContext context,
        UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", map);//The startElement method takes a String (the name of the tag) and the component to which
                                          //the tag corresponds (in this case, map).
        //After calling startElement, you can call writeAttribute to render the tag's attributes. The
      //writeAttribute method takes the name of the attribute, its value, and the name of a property
      //or attribute of the containing component corresponding to the attribute. The last parameter can
      //be null, and it won't be rendered.
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute(
            "name",
            getName(context, map),//The name attribute value of the map tag is retrieved using the getId method of UIComponent,
                                  //which returns the component's unique identifier. The name attribute value of the input tag is
                                  //retrieved using the getName(FacesContext, UIComponent) method of MapRenderer
            "clientId");
        writer.endElement("input");
        writer.endElement("map");
    }
    /*If you want your component to perform its own rendering but delegate to a renderer if there is
     one, include the following lines in the encoding method to check whether there is a renderer
     associated with this component:
*   if (getRendererType() != null) {
      super.encodeEnd(context);
      return;
      } 
    */
    
    
    

    // --------------------------------------------------------- Private Methods
    /**
     * <p>Return the calculated name for the hidden input field.</p>
     *
     * @param context Context for the current request
     * @param component Component we are rendering
     */
    private String getName(
        FacesContext context,
        UIComponent component) {
        return (component.getId() + "_current");
    }

    /**
     * <p>Return the context-relative path for the current page.</p>
     *
     * @param context Context for the current request
     */
    private String getURI(FacesContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalContext().getRequestContextPath());
        sb.append("/faces");
        sb.append(context.getViewRoot().getViewId());

        return (sb.toString());
    }
}
