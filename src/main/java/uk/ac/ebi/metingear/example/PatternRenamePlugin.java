package uk.ac.ebi.metingear.example;

import uk.ac.ebi.interfaces.entities.EntityCollection;
import uk.ac.ebi.interfaces.entities.Metabolite;
import uk.ac.ebi.interfaces.entities.Reconstruction;
import uk.ac.ebi.mdk.domain.tool.ReconstructionManager;
import uk.ac.ebi.metingear.view.ControlDialog;
import uk.ac.ebi.metingear.view.PlugableDialog;
import uk.ac.ebi.metingeer.interfaces.menu.ContextResponder;

import java.util.Arrays;
import java.util.List;

/**
 * @author John May
 */
public class PatternRenamePlugin implements PlugableDialog {

    @Override
    public List<String> getMenuPath() {
        return Arrays.asList("Tools", "Plugins");
    }

    @Override
    public Class<? extends ControlDialog> getDialogClass() {
        return PatternRename.class;
    }

    @Override
    public ContextResponder getContext() {
        return new ContextResponder() {
            @Override
            public boolean getContext(ReconstructionManager reconstructionManager, Reconstruction reconstruction, EntityCollection collection) {
                return collection.hasSelection(Metabolite.class);
            }
        };
    }
}
