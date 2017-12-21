package fr.mmyumu.wizardronin.game.system.render;

import fr.mmyumu.wizardronin.game.component.ArsenalData;

import java.io.Serializable;

/**
 * Repository for all cards.
 */
public class ArsenalLibrary implements Serializable {
    public ArsenalData[] arsenal;

    public ArsenalLibrary() {
    }

    /**
     * Return dilemma, or <code>null</code> if empty.
     */
    public ArsenalData getById(String id) {
        for (ArsenalData a : arsenal) {
            if (a.id != null && a.id.equals(id)) return a;
        }
        return null;
    }
}
