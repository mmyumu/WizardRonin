package fr.mmyumu.wizardronin.game.system.render;

import java.io.Serializable;

import fr.mmyumu.wizardronin.game.component.DialogData;

/**
 * Repository for all cards.
 */
public class DialogLibrary implements Serializable {
    public DialogData[] dialog;

    public DialogLibrary() {
    }

    /**
     * Return dilemma, or <code>null</code> if empty.
     */
    public DialogData getById(String id) {
        for (DialogData a : dialog) {
            if (a.id != null && a.id.equals(id)) return a;
        }
        return null;
    }
}
