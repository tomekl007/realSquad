/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package realsquad.ejb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;

/**
 *
 * @author Tomek
 */
@Named
@SessionScoped
public class AbstractBean implements Serializable {
    

    /**
     * <p>Return the
     * <code>FacesContext</code> instance for the current request.
     */
    /*FacesContext contains all of the per-request state information related to the 
     * processing of a single JavaServer Faces request, and the rendering
     * of the corresponding response*/
    /*A FacesContext instance is associated with a particular request at the beginning of 
     * request processing, by a call to the getFacesContext() method of the 
     * FacesContextFactory instance associated with the current web application. 
     * The instance remains active until its release() method is called, 
     * after which no further references to this instance are allowed. 
     * While a FacesContext instance is active, it must not be referenced 
     * from any thread other than the one upon which the servlet container
     * executing this web application utilizes for the processing of this request.*/
    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }

}