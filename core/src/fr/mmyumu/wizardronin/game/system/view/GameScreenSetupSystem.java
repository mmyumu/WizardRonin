package fr.mmyumu.wizardronin.game.system.view;

import com.artemis.Entity;
import com.artemis.annotations.Wire;
import net.mostlyoriginal.api.system.core.PassiveSystem;

import static com.artemis.E.E;

/**
 * @author Daan van Yperen
 */
@Wire
public class GameScreenSetupSystem extends PassiveSystem {

    GameScreenAssetSystem assetSystem;

    @Override
    protected void initialize() {
        createMousecursor();
    }

    private Entity createMousecursor() {
        return E()
                .pos()
                .mouseCursor()
                .bounds()
                .tag("cursor").entity();

    }


}
