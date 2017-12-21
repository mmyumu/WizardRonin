package fr.mmyumu.wizardronin.api.system.physics;

import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;

import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.component.Socket;
import fr.mmyumu.wizardronin.game.system.detection.DialogSystem;
import fr.mmyumu.wizardronin.game.system.map.EntitySpawnerSystem;
import fr.mmyumu.wizardronin.game.system.map.PowerSystem;
import fr.mmyumu.wizardronin.game.system.view.GameScreenAssetSystem;
import net.mostlyoriginal.api.component.graphics.Anim;
import fr.mmyumu.wizardronin.game.system.common.FluidIteratingSystem;
import fr.mmyumu.wizardronin.game.system.render.MyAnimRenderSystem;

import static com.artemis.E.E;

@Wire
public class SocketSystem extends FluidIteratingSystem {

    private PowerSystem powerSystem;
    private GameScreenAssetSystem assetSystem;
    private EntitySpawnerSystem entitySpawnerSystem;
    private MyAnimRenderSystem animSystem;
    private DialogSystem dialogSystem;

    public SocketSystem() {
        super(Aspect.all(Socket.class, Anim.class));
    }

    @Override
    protected void process(E e) {
        if (e.socketEntityId() != 0) {
            if (e.socketAnimSocketed() != null) {
                e.anim(e.socketAnimSocketed());
            }
        } else {
            if (e.socketAnimEmpty() != null) {
                e.anim(e.socketAnimEmpty());
            }
        }
    }

    public void socket(E battery, E socket) {
        unsocket(battery);
        assetSystem.playSfx(socket.socketSfxSocketed());

        if (socket.isRobot()) {
            battery.deleteFromWorld();
            socket.chargeIncrease(G.BARS_FOR_BATTERY);
            if (socket.hasSlumbering()) {
                animSystem.forceAnim(socket, "robot-wake-up");
                socket.removeSlumbering();
            } else {
                animSystem.forceAnim(socket, "robot-close-battery");
            }
        } else {
            socket.socketEntityId(battery.socketedInsideEntityId(socket.id()).invisible().id());
            power(socket, true);
        }
    }

    public void respawnRobotBatteries() {
        for (E socket : allEntitiesWith(Socket.class)) {
            if (socket.typeType().equals("battery2") && socket.socketEntityId() == 0 && !socket.isRobot()) {
                entitySpawnerSystem.spawnBatteryInSocket("battery2", socket);
            }
        }
    }

    public void power(E socket, boolean enable) {
        powerSystem.powerMapCoordsAround((int) (socket.posX() / G.CELL_SIZE + 0.5f), (int) (socket.posY() / G.CELL_SIZE + 0.5f), enable);
    }

    public void unsocket(E e) {
        if (e.hasSocketedInside()) {
            E socket = E(e.getSocketedInside().entityId);
            power(socket, false);
            socket.socketEntityId(0);
            e.removeSocketedInside().removeInvisible();
            assetSystem.playSfx(socket.socketSfxUnsocketed());
        }
    }
}
