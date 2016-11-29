package Vue;

import Cueillette.Modele;
import com.sun.javafx.sg.prism.NGShape;

import javax.swing.*;

/**
 * Created by Victor on 24/11/2016.
 */
public class VueControlStat extends JPanel implements Vue{
    protected Modele modele;

    public VueControlStat(Modele mod) {
        modele=mod;
    }

    @Override
    public void mettreAJour() {

    }
}
