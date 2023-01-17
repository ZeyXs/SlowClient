package fr.zeyx.slowclient.client.gui.option;

import fr.zeyx.slowclient.client.gui.option.control.Control;
import net.minecraft.text.Text;

public interface Option<T> {

    Text getName();

    Text getTooltip();

    T getValue();

    Control<T> getControl();

    void setValue(T value);

    void reset();

    boolean isAvailable();

    void setAvailable(boolean value);

}
