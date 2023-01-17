package fr.zeyx.slowclient.client.gui.option.control;

import fr.zeyx.slowclient.client.gui.option.Option;
import fr.zeyx.slowclient.util.Dim2i;

public interface Control<T> {

    Option<T> getOption();

    ControlElement<T> createElement(Dim2i dim);

    int getMaxWidth();

}
