/*
 The tag handler that implements the bookstore:map custom tag.
 * After you create your component and renderer classes, you’re ready to define how a tag handler
processes the tag representing the component and renderer combination.
In JavaServer Faces applications, the tag handler class associated with a component drives the
Render Response phase of the JavaServer Faces lifecycle.
* 
* The first thing that the tag handler does is to retrieve the type of the component associated with
the tag.Next, it sets the component’s attributes to the values given in the page. It then returns
the type of the renderer (if there is one) to the JavaServer Faces implementation so that the
component’s encoding can be performed when the tag is processed. Finally, it releases resources
used during the processing of the tag.
 */


package realsquad.taglib;

import realsquad.components.MapComponent;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.webapp.UIComponentELTag;
import realsquad.components.MapComponent;


/**
 * <p>{@link UIComponentELTag} for an image map.</p>
 */
//The class extends javax.faces.webapp.UIComponentELTag, the base class for all JavaServer
//Faces tags that correspond to a component.
public class MapTag extends UIComponentELTag {
    private MethodExpression action = null;
    private MethodExpression actionListener = null;
    private ValueExpression current = null;
    private ValueExpression immediate = null;
    private ValueExpression styleClass = null;

    public void setCurrent(ValueExpression current) {
        this.current = current;
    }

    /*Similarly, the setActionListener and setAction methods take javax.el.MethodExpression
objects, which means that these attributes accept method expressions. The following code
shows the properties used to access the values of the actionListener and the action attributes.*/
    public void setActionListener(MethodExpression actionListener) {
        this.actionListener = actionListener;
    }

    public void setAction(MethodExpression action) {
        this.action = action;
    }
    
    /*Before setting the values in the component class, the tag handler first gets the attribute values
from the page by means of JavaBeans component properties that correspond to the attributes.
The following code shows the property used to access the value of the immediate attribute.*/

    public void setImmediate(ValueExpression immediate) {
        /*As this code shows, the setImmediate method takes
         * a javax.el.ValueExpression object. This
means that the immediate attribute of the tag accepts value expressions*/
        this.immediate = immediate;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    /*As explained earlier, the first thing the MapTag tag 
     * handler class does is to retrieve the type of the
component. It does this by using the getComponentType method:*/
    public String getComponentType() {
        return ("DemoMap");
        /*The value returned from getComponentType must match the value configured for the
component with the componentType attribute of the @FacesComponent annotation*/
    }
/*After retrieving the type of the component, the tag handler sets the component’s property values
to those supplied as tag attributes values in the page.*/
    
    
    
    /*After setting the component properties, the tag handler provides a renderer type (if there is a
renderer associated with the component) to the JavaServer Faces implementation. It does this
using the getRendererType method:*/
    @Override
    public String getRendererType() {
        return ("DemoMap");//The renderer type that is returned is the name under which the renderer is registered with the application
    }/*If your component does not have a renderer associated with it, getRendererType should return
null. In this case, the rendererType element in the @FacesRenderer annotation should also be
set to null.*/
    

    @Override
    /*It is recommended that all tag handlers implement a release method, which releases resources
allocated during the execution of the tag handler by first calling the
UIComponentELTag.release method, then setting the resource values to null. The release
method of MapTag is as follows*/
    public void release() {
        super.release();
        current = null;
        styleClass = null;
        actionListener = null;
        action = null;
        immediate = null;
        styleClass = null;
    }

    /*}
To pass the value of the tag attributes to the component, the tag handler implements the
setProperties method. The way setProperties passes the attribute values to the component
class depends on whether the values are value expressions or method expressions.*/
    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        MapComponent map = (MapComponent) component;

        if (styleClass != null) {
            /*When the attribute value is a value expression, setProperties first determines whether it is a
literal expression. If the expression is not a literal, setProperties stores the expression into a
collection, from which the component class can retrieve it and resolve it at the appropriate time*/
            if (!styleClass.isLiteralText()) {
                map.setValueExpression("styleClass", styleClass);
            } 
            /*If the expression is a literal, setProperties performs any required type conversion and then
does one of the following:
■ If the attribute is renderer-independent, meaning that it is defined by the component class,
setProperties calls the corresponding setter method of the component class.
■ If the attribute is renderer-dependent, setProperties stores the converted value into the
component’s map of generic renderer attributes*/
            
         //The following piece of the MapTag handler's setProperties method sets the
         //renderer-dependent property, styleClass,
            else {
                map.getAttributes()//Return a mutable Map representing the attributes (and properties, see below) associated wth this UIComponent, keyed by attribute name (which must be a String)
                   .put(
                    "styleClass",
                    styleClass.getExpressionString());//Returns the original String used to create this Expression, unmodified.
            }
        }
 /*To handle the method expression referenced by actionListener, the setProperties method
must wrap the expression in a special action listener object called
MethodExpressionActionListener. This listener executes the method referenced by the
expression when it receives the action event. The setProperties method then adds this
MethodExpressionActionListener object to the list of listeners to be notified when the event
of a user clicking on the map occurs. The following piece of setProperties does all of this:*/
        if (actionListener != null) {
            map.addActionListener(
                    new MethodExpressionActionListener(actionListener));//Construct a ValueChangeListener that contains a MethodExpression
        }
/*If your component fires value change events, your tag handler’s setProperties method does a
similar thing, except it wraps the expression in a MethodExpressionValueChangeListener
object and adds the listener using the addValueChangeListener method.*/
        
        
/*In the case of the method expression referenced by the action attribute, the setProperties
method uses the setActionExpression method of ActionSource2 to set the corresponding
property on MapComponent:*/
        if (action != null) {
            map.setActionExpression(action);
        }

        /*The following piece of the MapTag handler's setProperties method sets , 
         * the renderer-independent property,immediate:*/
        if (immediate != null) {
            if (!immediate.isLiteralText()) {
                map.setValueExpression("immediate", immediate);
            } else {
                map.setImmediate(
                        Boolean.valueOf(immediate.getExpressionString()).booleanValue());
            }
        }
    }
}
