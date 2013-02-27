/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package realsquad.model;

import java.io.Serializable;

/**
 *
 * @author Tomek
 */
public class ImageArea implements Serializable {
    private String alt = null;
    private String coords = null;
    private String shape = null;

    /**
     * <p>Construct an uninitialized {@link ImageArea} instance.</p>
     */
    public ImageArea() {
    }

    /**
     * <p>Construct an {@link ImageArea} initialized with the specified property
     * values.</p>
     *
     * @param alt Alternate text for this hotspot
     * @param coords Coordinate positions for this hotspot
     * @param shape Shape of this hotspot (default, rect, circle, poly)
     */
    public ImageArea(
        String alt,
        String coords,
        String shape) {
        setAlt(alt);
        setCoords(coords);
        setShape(shape);
    }

    /**
     * <p>Return the alternate text for this hotspot.</p>
     */
    public String getAlt() {
        return (this.alt);
    }

    /**
     * <p>Set the alternate text for this hotspot.</p>
     *
     * @param alt The new alternate text
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * <p>Return the coordinate positions for this hotspot.</p>
     */
    public String getCoords() {
        return (this.coords);
    }

    /**
     * <p>Set the coordinate positions for this hotspot.</p>
     *
     * @param coords The new coordinate positions
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

    /**
     * <p>Return the shape for this hotspot.</p>
     */
    public String getShape() {
        return (this.shape);
    }

    /**
     * <p>Set the shape for this hotspot.</p>
     *
     * @param shape The new shape
     */
    public void setShape(String shape) {
        this.shape = shape;
    }
}
