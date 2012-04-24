package uk.ac.ebi.metingear.example;

import com.jgoodies.forms.layout.CellConstraints;
import uk.ac.ebi.caf.component.factory.FieldFactory;
import uk.ac.ebi.caf.component.factory.LabelFactory;
import uk.ac.ebi.caf.component.factory.PanelFactory;
import uk.ac.ebi.interfaces.entities.Metabolite;
import uk.ac.ebi.metingear.view.AbstractControlDialog;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author John May
 */
public class PatternRename extends AbstractControlDialog {

    private JTextField pattern = FieldFactory.newField(15);
    private JTextField replace = FieldFactory.newField(15);

    public PatternRename(Window window) {
        super(window);
    }

    @Override
    public JLabel createInformation() {
        return LabelFactory.newLabel("Rename metabolites using a pattern and a replacement");
    }

    @Override
    public JComponent createForm() {

        JPanel panel = PanelFactory.createDialogPanel("p, 4dlu, p:grow",
                                                      "p, 4dlu, p");

        CellConstraints cc = new CellConstraints();

        panel.add(LabelFactory.newFormLabel("Pattern:"), cc.xy(1, 1));
        panel.add(pattern,                               cc.xy(3, 1));

        panel.add(LabelFactory.newFormLabel("Replace:"), cc.xy(1, 3));
        panel.add(replace,                               cc.xy(3, 3));

        return panel;

    }

    @Override
    public void process() {

        // get the field values
        Pattern pattern     = Pattern.compile(this.pattern.getText());
        String  replacement = replace.getText();

        // iterate over selected metabolites and set the name
        // replacement if a match is found
        for(Metabolite metabolite : getSelection(Metabolite.class)){

            String  name    = metabolite.getName();
            Matcher matcher = pattern.matcher(name);

            if(matcher.find()){
                metabolite.setName(matcher.replaceAll(replacement));
            }

        }

    }

}


