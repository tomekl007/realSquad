/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package realsquad.taglib;


import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.webapp.UIComponentELTag;
import realsquad.components.AreaComponent;


/**
 * <p>{@link UIComponentELTag} for an image map hotspot.</p>
 */
public class AreaTag extends UIComponentELTag {
    private ValueExpression alt;
    private ValueExpression coords;
    private ValueExpression onmouseout;
    private ValueExpression onmouseover = null;
    private ValueExpression shape = null;
    private ValueExpression styleClass = null;
    private ValueExpression targetImage;
    private ValueExpression value = null;

    public void setAlt(ValueExpression alt) {
        this.alt = alt;
    }

    public void setTargetImage(ValueExpression targetImage) {
        this.targetImage = targetImage;
    }

    public void setCoords(ValueExpression coords) {
        this.coords = coords;
    }

    public void setOnmouseout(ValueExpression newonmouseout) {
        onmouseout = newonmouseout;
    }

    public void setOnmouseover(ValueExpression newonmouseover) {
        onmouseover = newonmouseover;
    }

    public void setShape(ValueExpression shape) {
        this.shape = shape;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setValue(ValueExpression newValue) {
        value = newValue;
    }

    @Override
    public String getComponentType() {
        return ("DemoArea");
    }

    @Override
    public String getRendererType() {
        return ("DemoArea");
    }

    @Override
    public void release() {
        super.release();
        this.alt = null;
        this.coords = null;
        this.onmouseout = null;
        this.onmouseover = null;
        this.shape = null;
        this.styleClass = null;
        this.value = null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        AreaComponent area = (AreaComponent) component;

        if (alt != null) {
            if (!alt.isLiteralText()) {
                area.setValueExpression("alt", alt);
            } else {
                area.setAlt(alt.getExpressionString());
            }
        }

        if (coords != null) {
            if (!coords.isLiteralText()) {
                area.setValueExpression("coords", coords);
            } else {
                area.setCoords(coords.getExpressionString());
            }
        }

        if (onmouseout != null) {
            if (!onmouseout.isLiteralText()) {
                area.setValueExpression("onmouseout", onmouseout);
            } else {
                area.getAttributes()
                    .put(
                    "onmouseout",
                    onmouseout.getExpressionString());
            }
        }

        if (onmouseover != null) {
            if (!onmouseover.isLiteralText()) {
                area.setValueExpression("onmouseover", onmouseover);
            } else {
                area.getAttributes()
                    .put(
                    "onmouseover",
                    onmouseover.getExpressionString());
            }
        }

        if (shape != null) {
            if (!shape.isLiteralText()) {
                area.setValueExpression("shape", shape);
            } else {
                area.setShape(shape.getExpressionString());
            }
        }

        if (styleClass != null) {
            if (!styleClass.isLiteralText()) {
                area.setValueExpression("styleClass", styleClass);
            } else {
                area.getAttributes()
                    .put(
                    "styleClass",
                    styleClass.getExpressionString());
            }
        }

        if (area instanceof ValueHolder) {
            ValueHolder valueHolder = (ValueHolder) component;

            if (value != null) {
                if (!value.isLiteralText()) {
                    area.setValueExpression("value", value);
                } else {
                    valueHolder.setValue(value.getExpressionString());
                }
            }
        }

        // target image is required
        area.setValueExpression("targetImage", targetImage);
    }
}
