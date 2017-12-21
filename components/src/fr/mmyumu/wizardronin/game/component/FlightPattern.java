package fr.mmyumu.wizardronin.game.component;

import com.artemis.Component;

/**
 * @author Daan van Yperen
 */
public class FlightPattern extends Component {
    public float age = 0;
    public FlightPatternData data;
    public int activeStep = 0;

    public FlightPattern() {
    }
}
