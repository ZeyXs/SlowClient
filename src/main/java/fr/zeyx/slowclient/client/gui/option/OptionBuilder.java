package fr.zeyx.slowclient.client.gui.option;

import fr.zeyx.slowclient.client.gui.option.control.Control;
import net.minecraft.text.Text;
import org.apache.commons.lang3.Validate;

import java.util.function.Function;

public class OptionBuilder<T> implements Option<T> {

    private final Control<T> control;
    private final Text name;
    private final Text tooltip;
    private boolean isAvailable;
    private T value;

    private OptionBuilder(T value, Text name, Text tooltip, Function<OptionBuilder<T>, Control<T>> control, boolean isAvailable) {
        this.name = name;
        this.tooltip = tooltip;
        this.control = control.apply(this);
        this.isAvailable = isAvailable;
        this.value = value;

        //this.reset();
    }

    @Override
    public Text getName() {
        return this.name;
    }

    @Override
    public Text getTooltip() {
        return this.tooltip;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public Control<T> getControl() {
        return this.control;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void reset() {
        // this.value = this.binding.getValue(this.storage.getData()); // TODO: Storage system
        //this.modifiedValue = this.value;
    }

    @Override
    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Override
    public void setAvailable(boolean value) {
        this.isAvailable = value;
    }

    public static <T> OptionBuilder.Builder<T> createBuilder(Class<T> type) {
        return new Builder<>();
    }

    public static class Builder<T> {
        private T value;
        private Text name;
        private Text tooltip;
        private Function<OptionBuilder<T>, Control<T>> control;
        private boolean isAvailable;

        public Builder<T> setName(Text name) {
            Validate.notNull(name, "Argument must not be null.");
            this.name = name;
            return this;
        }

        public Builder<T> setTooltip(Text tooltip) {
            Validate.notNull(tooltip, "Argument must not be null.");
            this.tooltip = tooltip;
            return this;
        }

        public Builder<T> setControl(Function<OptionBuilder<T>, Control<T>> control) {
            Validate.notNull(control, "Argument must not be null.");
            this.control = control;
            return this;
        }

        public Builder<T> setDefaultValue(T value) {
            this.value = value;
            return this;
        }

        public Builder<T> setAvailable(boolean value) {
            this.isAvailable = value;
            return this;
        }

        public OptionBuilder<T> build() {
            Validate.notNull(this.value, "Type must be specified.");
            Validate.notNull(this.name, "Name must be specified.");
            Validate.notNull(this.tooltip, "Tooltip must be specified.");
            Validate.notNull(this.control, "Control must be specified.");

            return new OptionBuilder<>(this.value, this.name, this.tooltip, this.control, this.isAvailable);
        }
    }

}
