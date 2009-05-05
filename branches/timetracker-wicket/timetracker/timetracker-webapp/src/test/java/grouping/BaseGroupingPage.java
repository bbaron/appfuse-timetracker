package grouping;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Simple test using the WicketTester
 */
public abstract class BaseGroupingPage extends WebPage {
    public static final String DEXTER = "Omelette du fromage";
    public static final String DEEDEE = "Is thas all you can say?";

    protected static MarkupContainer addLabelsTo(MarkupContainer mc) {
        return
        mc.add(new Label("dexter", DEXTER))
          .add(new Label("deedee", DEEDEE));
    }

}
