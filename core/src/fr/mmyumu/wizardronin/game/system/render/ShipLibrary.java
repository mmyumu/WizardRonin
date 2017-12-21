package fr.mmyumu.wizardronin.game.system.render;

import java.io.Serializable;

import fr.mmyumu.wizardronin.game.component.ArsenalData;
import fr.mmyumu.wizardronin.game.component.ShipData;

/**
 * Repository for all cards.
 */
public class ShipLibrary implements Serializable {
    public ShipData[] ships;
    public ArsenalData[] arsenalData;

    public ShipLibrary() {
    }

    /**
     * Return dilemma, or <code>null</code> if empty.
     */
    public ShipData getById(String id) {
        for (ShipData ship : ships) {
            if (ship.id != null && ship.id.equals(id)) return ship;
        }
        return null;
    }
}
