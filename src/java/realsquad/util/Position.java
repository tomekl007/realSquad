/*
 enum with all avalible positions
 */
package realsquad.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Tomek
 */
public enum Position {
    GK,
    CD,
    LD,
    RD,
    DM,
    AM,
    LW,
    RW,
    CF;
      public String toString(Locale locale) {
        ResourceBundle res = ResourceBundle.getBundle(
                    "realsquad.util.PositionMessages",
                    locale);

        return res.getString(name() + ".string");//name() -  Returns the name of this enum constant, exactly as declared in its
       // enum declaration. np wywolywane na rzecz StatusType IN -> zwracam IN.string z res(ResourceBundle)
    }

    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle(
                    "realsquad.util.PositionMessages",
                    locale);

        return res.getString(name() + ".string");
    }
    
}
