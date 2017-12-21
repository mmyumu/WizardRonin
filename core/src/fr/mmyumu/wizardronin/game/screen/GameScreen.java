package fr.mmyumu.wizardronin.game.screen;

import com.artemis.SuperMapper;
import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.graphics.Color;

import net.mostlyoriginal.api.manager.FontManager;
import net.mostlyoriginal.api.screen.core.WorldScreen;
import net.mostlyoriginal.api.system.camera.CameraShakeSystem;
import net.mostlyoriginal.api.system.camera.CameraSystem;
import net.mostlyoriginal.api.system.graphics.RenderBatchingSystem;
import net.mostlyoriginal.api.system.physics.AttachmentSystem;
import net.mostlyoriginal.api.system.physics.CollisionSystem;
import net.mostlyoriginal.api.system.physics.GravitySystem;
import net.mostlyoriginal.api.system.physics.PhysicsSystem;
import net.mostlyoriginal.api.system.render.ClearScreenSystem;
import net.mostlyoriginal.plugin.OperationsPlugin;
import net.mostlyoriginal.plugin.ProfilerPlugin;

import fr.mmyumu.wizardronin.api.system.physics.CarriedSystem;
import fr.mmyumu.wizardronin.api.system.physics.SocketSystem;
import fr.mmyumu.wizardronin.game.GdxArtemisGame;
import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.system.FlightPatternControlSystem;
import fr.mmyumu.wizardronin.game.system.FollowSystem;
import fr.mmyumu.wizardronin.game.system.FootstepSystem;
import fr.mmyumu.wizardronin.game.system.JumpAttackSystem;
import fr.mmyumu.wizardronin.game.system.ShipControlSystem;
import fr.mmyumu.wizardronin.game.system.TerminalSystem;
import fr.mmyumu.wizardronin.game.system.detection.BirdBrainSystem;
import fr.mmyumu.wizardronin.game.system.detection.DeathSystem;
import fr.mmyumu.wizardronin.game.system.detection.DialogSystem;
import fr.mmyumu.wizardronin.game.system.detection.ExitSystem;
import fr.mmyumu.wizardronin.game.system.detection.FarewellSystem;
import fr.mmyumu.wizardronin.game.system.detection.ParticleSystem;
import fr.mmyumu.wizardronin.game.system.detection.PickupSystem;
import fr.mmyumu.wizardronin.game.system.detection.SpoutSystem;
import fr.mmyumu.wizardronin.game.system.detection.TriggerSystem;
import fr.mmyumu.wizardronin.game.system.map.EntitySpawnerSystem;
import fr.mmyumu.wizardronin.game.system.map.HealthUISystem;
import fr.mmyumu.wizardronin.game.system.map.MapCollisionSystem;
import fr.mmyumu.wizardronin.game.system.map.MapRenderInFrontSystem;
import fr.mmyumu.wizardronin.game.system.map.MapRenderSystem;
import fr.mmyumu.wizardronin.game.system.map.MapSystem;
import fr.mmyumu.wizardronin.game.system.map.PlatformCollisionSystem;
import fr.mmyumu.wizardronin.game.system.map.PowerSystem;
import fr.mmyumu.wizardronin.game.system.map.PriorityAnimSystem;
import fr.mmyumu.wizardronin.game.system.map.RenderBackgroundSystem;
import fr.mmyumu.wizardronin.game.system.map.WallSensorSystem;
import fr.mmyumu.wizardronin.game.system.render.AdditiveRenderSystem;
import fr.mmyumu.wizardronin.game.system.render.BoundingBoxRenderSystem;
import fr.mmyumu.wizardronin.game.system.render.CameraClampToMapSystem;
import fr.mmyumu.wizardronin.game.system.render.CameraFollowSystem;
import fr.mmyumu.wizardronin.game.system.render.CameraUnfreezeSystem;
import fr.mmyumu.wizardronin.game.system.render.EnemyCleanupSystem;
import fr.mmyumu.wizardronin.game.system.render.MyAnimRenderSystem;
import fr.mmyumu.wizardronin.game.system.render.MyLabelRenderSystem;
import fr.mmyumu.wizardronin.game.system.render.TransitionSystem;
import fr.mmyumu.wizardronin.game.system.view.ArsenalDataSystem;
import fr.mmyumu.wizardronin.game.system.view.DialogDataSystem;
import fr.mmyumu.wizardronin.game.system.view.FlightPatternDataSystem;
import fr.mmyumu.wizardronin.game.system.view.GameScreenAssetSystem;
import fr.mmyumu.wizardronin.game.system.view.GameScreenSetupSystem;
import fr.mmyumu.wizardronin.game.system.view.ShipDataSystem;

/**
 * Example main game screen.
 *
 * @author Daan van Yperen
 */
public class GameScreen extends WorldScreen {

    public static final String BACKGROUND_COLOR_HEX = "0000FF";

    @Override
    protected World createWorld() {
        RenderBatchingSystem renderBatchingSystem;
        return new World(new WorldConfigurationBuilder()
                .dependsOn(EntityLinkManager.class, ProfilerPlugin.class, OperationsPlugin.class)
                .with(
                        new SuperMapper(),

                        new GameScreenAssetSystem(),
                        /*
                        new SuperMapper(),
                        new TagManager(),
                        new GroupManager(),

                        new EntitySpawnerSystem(),
                        new MapSystem(),
                        new ParticleSystem(),
                        new PowerSystem(),
                        new DialogSystem(),

                        new GameScreenAssetSystem(),
                        new ArsenalDataSystem(),
                        new ShipDataSystem(),
                        new DialogDataSystem(),
                        new FlightPatternDataSystem(),
                        new GameScreenSetupSystem(),
                        new FontManager(),

                        // sensors.
                        new WallSensorSystem(),
                        new CollisionSystem(),

                        // spawn
                        new TriggerSystem(),
                        new FarewellSystem(),
                        new SpoutSystem(),

                        // Control and logic.
                        new CameraUnfreezeSystem(),
                        new EnemyCleanupSystem(),
                        new FollowSystem(),
                        new FlightPatternControlSystem(),
                        new ShipControlSystem(),
                        new AttachmentSystem(),
                        new BirdBrainSystem(),

                        // Physics.
                        new GravitySystem(),
                        new MapCollisionSystem(),
                        new PlatformCollisionSystem(),
                        new PhysicsSystem(),

                        // Effects.
                        new FootstepSystem(),
                        new CarriedSystem(),
                        new SocketSystem(),
                        new PickupSystem(),

                        // Camera.
                        new CameraFollowSystem(),
                        new CameraShakeSystem(),
                        new CameraClampToMapSystem(),
                        new CameraSystem(G.CAMERA_ZOOM),
                        new PriorityAnimSystem(),

                        new JumpAttackSystem(),

                        new ClearScreenSystem(Color.valueOf("000000")),
                        new RenderBackgroundSystem(),
                        new MapRenderSystem(),

                        renderBatchingSystem = new RenderBatchingSystem(),
                        new MyAnimRenderSystem(renderBatchingSystem),
                        new BoundingBoxRenderSystem(renderBatchingSystem),
                        new MyLabelRenderSystem(renderBatchingSystem),
                        new AdditiveRenderSystem(),
                        new MapRenderInFrontSystem(),
                        new TerminalSystem(),
                        new ExitSystem(),
                        new DeathSystem(),
                        new HealthUISystem(),
                        new TransitionSystem(GdxArtemisGame.getInstance())
                )
                */
                .build());
    }

}
