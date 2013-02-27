/*
 This Renderer performs the delegated rendering for AreaComponent
 */


package realsquad.renderers;


import realsquad.model.ImageArea;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import realsquad.components.AreaComponent;
import realsquad.components.MapComponent;


/**
 * <p>This class converts the internal representation of a
 * <code>UIArea</code> component into the output stream associated with the
 * response to a particular request.</p>
 */
//The renderer class begins with a @FacesRenderer annotation:
//The @FacesRenderer annotation registers the renderer class with the JavaServer Faces
//implementation as a renderer class
//The annotation identifies the component family as well as
//the renderer type. componentFamily jest zwracane przez AreaTag.getRendererType()
@FacesRenderer(componentFamily = "Area", rendererType = "DemoArea")
public class AreaRenderer extends Renderer {
    //AreaRenderer contains an empty constructor. This is
//used to create an instance of AreaRenderer so that it can be added to the render kit.
    public AreaRenderer() {
    }

    // Renderer Methods
    /**
     * <p>No decoding is required.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    @Override
    public void decode(
        FacesContext context,
        UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
    }

    /**
     * <p>No begin encoding is required.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    @Override
    public void encodeBegin(
        FacesContext context,
        UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
    }

    /**
     * <p>No children encoding is required.</p>
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
     * <p>Encode this component.</p>
     *
     * @param context
     * <code>FacesContext</code>for the current request
     * @param component
     * <code>UIComponent</code> to be decoded
     */
    /*To perform the rendering for AreaComponent, AreaRenderer must implement an encodeEnd
method. The encodeEnd method of AreaRenderer retrieves the shape, coordinates, and
alternative text values stored in the ImageArea bean that is bound to AreaComponent. */
    
    
    @Override
    public void encodeEnd(
        FacesContext context,
        UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        AreaComponent area = (AreaComponent) component;
        String targetImageId = area.findComponent(area.getTargetImage())
                                   .getClientId(context);
       // The AreaRenderer class, which needs to render the alt, shape,
       //and coords values from the ImageArea object, calls the getValue method of AreaComponent to
       //retrieve the ImageArea object.
        ImageArea iarea = (ImageArea) area.getValue();
        ResponseWriter writer = context.getResponseWriter();
        StringBuilder sb = null;
       //ImageArea is a simple bean, so you can access the shape, coordinates, and alternative text values 
       //by calling the appropriate accessor methods of ImageArea
        
        /*After retrieving the ImageArea object, the method renders the values for shape, coords, and
alt by simply calling the associated accessor methods and passing the returned values to the
ResponseWriter instance, as shown by these lines of code, which write out the shape and
coordinates:*/
        
        //<area alt="Jeeves" coords="337,23,480,268" shape="rect" 
        //onmouseout="document.forms[0]['j_idt13:mapImage'].src='resources/images/book_all.jpg'" 
        //onmouseover="document.forms[0]['j_idt13:mapImage'].src='resources/images/book_202.jpg'" 
        //onclick="document.forms[0]['bookMap_current'].value='Jeeves'; document.forms[0].submit()" />
        writer.startElement("area", area);
        writer.writeAttribute(
            "alt",
            iarea.getAlt(),
            "alt");
        writer.writeAttribute(
            "coords",
            iarea.getCoords(),
            "coords");
        writer.writeAttribute(
            "shape",
            iarea.getShape(),
            "shape");
        /*The encodeEnd method also renders the JavaScript for the onmouseout, onmouseover, and
           onclick attributes. The Facelets page need only provide the path to the images that are to be
           loaded during an onmouseover or onmouseout action:
<bookstore:area id="map3" value="#{Book203}"
onmouseover="resources/images/book_203.jpg"
onmouseout="resources/images/book_all.jpg"
targetImage="mapImage"/>*/
        
        
        /*The AreaRenderer class takes care of generating the JavaScript for these actions, as shown in the
following code from encodeEnd. The JavaScript that AreaRenderer generates for the onclick
action sets the value of the hidden field to the value of the current area's component ID and
submits the page.*/
        sb = new StringBuilder("document.forms[0]['").append(targetImageId)
                                                     .append("'].src='");
        sb.append(
                getURI(
                        context,
                        (String) area.getAttributes().get("onmouseout")));//getAttributes() - Return a mutable Map representing the attributes (and properties, see below) associated wth this UIComponent, keyed by attribute name (which must be a String). 
        sb.append("'");
        writer.writeAttribute(
            "onmouseout",
            sb.toString(),
            "onmouseout");
        sb = new StringBuilder("document.forms[0]['").append(targetImageId)
                                                     .append("'].src='");
        sb.append(
                getURI(
                        context,
                        (String) area.getAttributes().get("onmouseover")));
        sb.append("'");
        writer.writeAttribute(
            "onmouseover",
            sb.toString(),
            "onmouseover");
        sb = new StringBuilder("document.forms[0]['");
        sb.append(getName(context, area));
        sb.append("'].value='");
        sb.append(iarea.getAlt());
        sb.append("'; document.forms[0].submit()");
        writer.writeAttribute(
            "onclick",
            sb.toString(),
            "value");
        writer.endElement("area");
    }/*By submitting the page, this code causes the JavaServer Faces life cycle to return back to the
Restore View phase. This phase saves any state information, including the value of the hidden
field, so that a new request component tree is constructed. This value is retrieved by the decode
method of the MapComponent class. This decode method is called by the JavaServer Faces
implementation during the Apply Request Values phase, which follows the Restore View phase.*/
    
    

    /**
     * <p>Return the calculated name for the hidden input field.</p>
     *
     * @param context Context for the current request
     * @param component Component we are rendering
     */
    private String getName(
        FacesContext context,
        UIComponent component) {
        while (component != null) {
            if (component instanceof MapComponent) {
                return (component.getId() + "_current");
            }

            component = component.getParent();
        }

        throw new IllegalArgumentException();
    }

    /**
     * <p>Return the path to be passed into JavaScript for the specified
     * value.</p>
     *
     * @param context Context for the current request
     * @param value Partial path to be (potentially) modified
     */
    private String getURI(
        FacesContext context,
        String value) {
        if (value.startsWith("/")) {
            return (context.getExternalContext()
                           .getRequestContextPath() + value);
        } else {
            return (value);
        }
    }
}
