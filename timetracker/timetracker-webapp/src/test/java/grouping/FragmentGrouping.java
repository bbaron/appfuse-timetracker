package grouping;

import org.apache.wicket.markup.html.panel.Fragment;

public class FragmentGrouping extends BaseGroupingPage {

    public class LabelsFragment extends Fragment {

        public LabelsFragment(String id) {
            super(id, "fragment", FragmentGrouping.this);
            addLabelsTo(this);
        }
        
    }
    
    public FragmentGrouping() {
        add(new LabelsFragment("group"));
    }
    
}
