package fr.mmyumu.wizardronin.game.system.render;

import java.io.Serializable;

import fr.mmyumu.wizardronin.game.component.FlightPatternData;

/**
 * Repository for all cards.
 */
public class FlightPatternLibrary implements Serializable {
    public FlightPatternData[] patterns;

    public FlightPatternLibrary() {
    }

    /**
     * Return dilemma, or <code>null</code> if empty.
     */
    public FlightPatternData getById(String id) {
        for (FlightPatternData pattern : patterns) {
            if (pattern.id != null && pattern.id.equals(id)) return pattern;
        }
        return null;
    }
}
