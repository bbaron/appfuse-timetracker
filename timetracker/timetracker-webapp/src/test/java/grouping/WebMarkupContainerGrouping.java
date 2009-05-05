package grouping;

import org.apache.wicket.markup.html.WebMarkupContainer;

public class WebMarkupContainerGrouping extends BaseGroupingPage {

    public WebMarkupContainerGrouping() {
        WebMarkupContainer group = new WebMarkupContainer("group");
        addLabelsTo(group);
        add(group);
    }
    
}
