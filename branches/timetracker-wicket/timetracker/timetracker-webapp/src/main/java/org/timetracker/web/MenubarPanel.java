package org.timetracker.web;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenubarPanel extends Panel {

    public MenubarPanel() {
        super("menubar");
        add(new Link("timecard") {

            @Override
            public void onClick() {
                setResponsePage(new Timecard());
            }
            
        });

    }

}
