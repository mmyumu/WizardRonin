package fr.mmyumu.wizardronin.game.system.render;

import com.artemis.Aspect;
import com.artemis.E;

import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.system.common.FluidIteratingSystem;
import net.mostlyoriginal.api.component.basic.Pos;
import net.mostlyoriginal.api.component.physics.Frozen;
import net.mostlyoriginal.api.system.camera.CameraSystem;

/**
 * Unfreeze near camera.
 *
 * @author Daan van Yperen
 */
public class CameraUnfreezeSystem extends FluidIteratingSystem {

    CameraSystem cameraSystem;
    private MyAnimRenderSystem myAnimRenderSystem;
    private boolean lockCamera;
    private CameraFollowSystem cameraFollowSystem;
    private float maxY;

    public CameraUnfreezeSystem() {
        super(Aspect.all(Pos.class, Frozen.class));
    }

    @Override
    protected void begin() {
        super.begin();
        final E camera = entityWithTag("camera");
        maxY = cameraFollowSystem.minCameraY() + (G.SCREEN_HEIGHT / G.CAMERA_ZOOM);
    }

    @Override
    protected void process(E e) {
        if ( e.posY() <= maxY ) {
            e.removeFrozen();
        }
    }
}

