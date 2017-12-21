package fr.mmyumu.wizardronin.game.system.detection;

import com.artemis.Aspect;
import com.artemis.E;
import com.badlogic.gdx.math.Vector2;

import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.component.GunData;
import fr.mmyumu.wizardronin.game.system.map.EntitySpawnerSystem;
import fr.mmyumu.wizardronin.game.system.view.GameScreenAssetSystem;
import net.mostlyoriginal.api.component.basic.Pos;
import net.mostlyoriginal.api.component.physics.Frozen;
import fr.mmyumu.wizardronin.game.component.Spout;
import fr.mmyumu.wizardronin.game.system.common.FluidIteratingSystem;

/**
 * @author Daan van Yperen
 */
public class SpoutSystem extends FluidIteratingSystem {

    private ParticleSystem particleSystem;
    private EntitySpawnerSystem entitySpawnerSystem;
    private GameScreenAssetSystem assetSystem;

    public SpoutSystem() {
        super(Aspect.all(Spout.class, Pos.class).exclude(Frozen.class));
    }

    Vector2 v2 = new Vector2();

    @Override
    protected void process(E e) {
        if ( !e.hasShooting() ) {
            e.spoutAge(0);
            e.spoutSprayCooldown(e.spoutSprayCooldown() - world.delta);
            return;
        }
        e.spoutAge(e.spoutAge() + world.delta);
        if (e.spoutAge() >= e.spoutCooldown() + e.spoutSprayDuration()) {
            e.spoutSprayCooldown(0);
            e.spoutAge(0);
        }
        if (e.spoutAge() < e.spoutSprayDuration()) {
            e.spoutSprayCooldown(e.spoutSprayCooldown() - world.delta);
            if (e.spoutSprayCooldown() <= 0) {
                e.spoutSprayCooldown(e.spoutSprayInterval());
                float angle = e.spoutAngle() + e.angleRotation(); //+ MathUtils.random(-2f, 2f);

                // rotate to parent orientation.
                if ( e.hasAttached() ) {
                    if ( e.attachedParent() > 0 )
                    {
                        E parent = E.E(e.attachedParent());
                        if ( parent != null ) {
                            angle += parent.angleRotation();
                        }
                    }
                }

                v2.set(e.posX() + e.boundsCx(), e.posY() + e.boundsCy());

                switch (e.spoutType()) {
                    case BULLET:
                        spawnBullet(angle, v2.x, v2.y, e.gunData().speed, 0, G.CAMERA_SCROLL_SPEED, e.teamTeam(), e.gunData().bounces, e.gunData());
                        break;
                    case GREMLIN:
                        if (playerWithInRange(v2.x, v2.y)) {
                            spawnGremlin(angle, v2.x, v2.y);
                        }
                        break;
                }

            }
        }
    }

    private boolean playerWithInRange(float x, float y) {
        float range = entityWithTag("player").posXy().dst2(x, y, 0);
        return range < 224 * 224 && range > 48 * 48;
    }

    public void spawnGremlin(float angle, float x, float y) {
        entitySpawnerSystem.spawnGremlin(x - 12, y - 12);
    }

    private void spawnBullet(float angle, float x, float y, int force, float emitterVx, float emitterVy, int team, int bounce, GunData gunData) {
        particleSystem.bullet(x, y, angle, force, emitterVx, emitterVy, team, bounce, gunData);
        assetSystem.playSfx("pew");
    }
}
