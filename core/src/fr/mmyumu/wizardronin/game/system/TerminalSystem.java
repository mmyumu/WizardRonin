package fr.mmyumu.wizardronin.game.system;

import com.artemis.Aspect;
import com.artemis.E;

import fr.mmyumu.wizardronin.game.system.common.FluidIteratingSystem;
import fr.mmyumu.wizardronin.game.component.Terminal;

/**
 * @author Daan van Yperen
 */
public class TerminalSystem extends FluidIteratingSystem {
    public TerminalSystem() {
        super(Aspect.all(Terminal.class));
    }

    @Override
    protected void process(E e) {
        e.deleteFromWorld();
    }
}
