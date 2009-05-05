package grouping;

import static grouping.BaseGroupingPage.*;

import java.util.Arrays;
import java.util.Collection;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.timetracker.web.WicketApplication;

@RunWith(Parameterized.class)
public class BaseGroupingTest {
    protected WicketTester tester;
    private Class<? extends BaseGroupingPage> webPageClass;

    public BaseGroupingTest(Class<? extends BaseGroupingPage> webPageClass) {
        this.webPageClass = webPageClass;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                    { NoGrouping.class} 
                  , { WebMarkupContainerGrouping.class } 
                  , { PanelGrouping.class } 
                  , { FragmentGrouping.class } 
                });
    }

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
        tester.startPage(webPageClass);
    }

    @Test
    public void pageRendered() {
        tester.assertRenderedPage(webPageClass);
        String pathPrefix = ((webPageClass == NoGrouping.class) ? "" : "group:");
        tester.assertLabel(pathPrefix + "dexter", DEXTER);
        tester.assertLabel(pathPrefix + "deedee", DEEDEE);
    }

}
