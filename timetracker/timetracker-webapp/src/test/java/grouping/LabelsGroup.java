package grouping;

import org.apache.wicket.markup.html.panel.Panel;
import static grouping.BaseGroupingPage.*;

public class LabelsGroup extends Panel {

    public LabelsGroup(String id) {
        super(id);
        addLabelsTo(this);
    }

}
