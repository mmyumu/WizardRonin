package fr.mmyumu.wizardronin.game.system.view;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import fr.mmyumu.wizardronin.game.system.render.DialogLibrary;
import fr.mmyumu.wizardronin.game.component.DialogData;

/**
 * @author Daan van Yperen
 */
@Wire
public class DialogDataSystem extends BaseSystem {

    private DialogLibrary library;

    public DialogDataSystem() {
        super();
        load();
    }

    @Override
    protected void processSystem() {
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    private void load() {
        final Json json = new Json();
        library = json.fromJson(DialogLibrary.class, Gdx.files.internal("dialog.json"));
    }

    public DialogData get(String id ) {
        return library.getById(id);
    }
}
