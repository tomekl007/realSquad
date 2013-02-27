/*
 AreaComponent is bound to a bean that stores the shape and coordinates of the region of the
image map. You'll see how all this data is accessed through the value expression in “Creating the
Renderer Class” on page 271. The behavior of AreaComponent consists of the following:
■ Retrieving the shape and coordinate data from the bean
■ Setting the value of the hidden tag to the id of this component
■ Rendering the area tag, including the JavaScript for the onmouseover, onmouseout, and
onclick functions
 */
package realsquad.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import realsquad.model.ImageArea;

/**
 *
 * @author Tomek
 */
//The AreaComponent class extends the standard component UIOutput. The @FacesComponent
//annotation registers the components with the JavaServer Faces implementation:
@FacesComponent("DemoArea")
public class AreaComponent extends UIOutput {
    private String alt = null;
    private String coords = null;
    private String shape = null;
    private String targetImage = null;

    /**
     * <p>Return the alternate text for our synthesized {@link ImageArea}.</p>
     */
    public String getAlt() {
        return (this.alt);
    }

    /**
     * <p>Set the alternate text for our synthesized {@link ImageArea}.</p>
     *
     * @param alt The new alternate text
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * <p>Return the hotspot coordinates for our synthesized {@link ImageArea}.
     * </p>
     */
    public String getCoords() {
        return (this.coords);
    }

    /**
     * <p>Set the hotspot coordinates for our synthesized {@link ImageArea}.</p>
     *
     * @param coords The new coordinates
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

    /**
     * <p>Return the shape for our synthesized {@link ImageArea}.</p>
     */
    public String getShape() {
        return (this.shape);
    }

    /**
     * <p>Set the shape for our synthesized {@link ImageArea}.</p>
     *
     * @param shape The new shape (default, rect, circle, poly)
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * <p>Set the image that is the target of this
     * <code>AreaComponent</code>.</p>
     *
     * @return the target image of this area component.
     */
    public String getTargetImage() {
        return targetImage;
    }

    /**
     * <p>Set the image that is the target of this
     * <code>AreaComponent</code>.</p>
     *
     * @param targetImage the ID of the target of this
     * <code>AreaComponent</code>
     */
    public void setTargetImage(String targetImage) {
        this.targetImage = targetImage;
    }

    /**
     * <p>Return the component family for this component.</p>
     */
    @Override
    public String getFamily() {
        return ("Area");
    }

    // UIOutput Methods
    /**
     * <p>Synthesize and return an {@link ImageArea} bean for this hotspot, if
     * there is no
     * <code>valueRef</code> property on this component.</p>
     */
    //To reference the ImageArea model object bean values from the component class, you
    //implement a getValue method in the component class. This method calls super.getValue.
    @Override
    public Object getValue() { //getValue method that does the work of finding the ImageArea object associated with AreaComponent.
        if (super.getValue() == null) {
            setValue(
                    new ImageArea(
                        getAlt(),
                        getCoords(),
                        getShape()));
        }

        return (super.getValue());
    }

    // StateHolder Methods
    /**
     * <p>Return the state to be saved for this component.</p>
     *
     * @param context
     * <code>FacesContext</code> for the current request
     */
    @Override
    public Object saveState(FacesContext context) {
        Object[] values = new Object[5];
        values[0] = super.saveState(context);
        values[1] = alt;
        values[2] = coords;
        values[3] = shape;
        values[4] = targetImage;

        return (values);
    }

    /**
     * <p>Restore the state for this component.</p>
     *
     * @param context
     * <code>FacesContext</code> for the current request
     * @param state State to be restored
     *
     * @throws IOException if an input/output error occurs
     */
    @Override
    public void restoreState(
        FacesContext context,
        Object state) {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        alt = (String) values[1];
        coords = (String) values[2];
        shape = (String) values[3];
        targetImage = (String) values[4];
    }
}
