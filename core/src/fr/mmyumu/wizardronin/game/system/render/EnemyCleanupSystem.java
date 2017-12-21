package fr.mmyumu.wizardronin.game.system.render;

import com.artemis.Aspect;
import com.artemis.E;
import net.mostlyoriginal.api.component.basic.Pos;
import net.mostlyoriginal.api.system.camera.CameraSystem;

import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.component.Team;
import fr.mmyumu.wizardronin.game.system.common.FluidIteratingSystem;

/**
 * Unfreeze near camera.
 *
 * @author Daan van Yperen
 */
public class EnemyCleanupSystem extends FluidIteratingSystem {

    CameraSystem cameraSystem;
    private MyAnimRenderSystem myAnimRenderSystem;
    private boolean lockCamera;
    private CameraFollowSystem cameraFollowSystem;
    private float minY;

    public EnemyCleanupSystem() {
        super(Aspect.all(Pos.class, Team.class));
    }

    @Override
    protected void begin() {
        super.begin();
        final E camera = entityWithTag("camera");
        minY = cameraFollowSystem.minCameraY() - 60;
    }

    @Override
    protected void process(E e) {
        if ( e.teamTeam() == G.TEAM_ENEMIES && e.posY() <= minY ) {
            e.deleteFromWorld();
        }
    }
}

