package fr.mmyumu.wizardronin.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import fr.mmyumu.wizardronin.game.screen.GameScreen;
import fr.mmyumu.wizardronin.game.component.G;
import fr.mmyumu.wizardronin.game.component.Settings;

public class GdxArtemisGame extends Game {

    private static GdxArtemisGame instance;

    @Override
    public void create() {
        instance = this;
        G.settings = (new Json()).fromJson(Settings.class, Gdx.files.internal("settings.json"));

        restart();
    }

    public void restart() {
        G.level = G.settings.startingLevel;
        setScreen(new GameScreen());
    }

    public static GdxArtemisGame getInstance() {
        return instance;
    }
}
