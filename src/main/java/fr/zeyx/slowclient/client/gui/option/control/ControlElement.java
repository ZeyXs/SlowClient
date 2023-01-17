package fr.zeyx.slowclient.client.gui.option.control;

import fr.zeyx.slowclient.client.gui.option.Option;
import fr.zeyx.slowclient.client.gui.widget.AbstractWidget;
import fr.zeyx.slowclient.util.Dim2i;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

public class ControlElement<T> extends AbstractWidget {

    protected final Option<T> option;
    protected final Dim2i dim;

    protected boolean hovered;

    public ControlElement(Option<T> option, Dim2i dim) {
        this.option = option;
        this.dim = dim;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        String name = this.option.getName().getString();
        String label;

        if (this.hovered && this.font.getWidth(name) > (this.dim.width() - this.option.getControl().getMaxWidth())) {
            name = name.substring(0, Math.min(name.length(), 10)) + "...";
        }

        if (this.option.isAvailable()) {
            label = Formatting.WHITE + name;
        } else {
            label = String.valueOf(Formatting.GRAY) + Formatting.STRIKETHROUGH + name;
        }

        this.hovered = this.dim.containsCursor(mouseX, mouseY);

        this.drawRect(this.dim.x(), this.dim.y(), this.dim.getLimitX(), this.dim.getLimitY(), this.hovered ? 0xE0000000 : 0x90000000);
        this.drawString(matrices, label, this.dim.x() + 6, this.dim.getCenterY() - 4, 0xFFFFFFFF);

    }

    public boolean isHovered() {
        return this.hovered;
    }

    public Option<T> getOption() {
        return option;
    }

    public Dim2i getDimensions() {
        return this.dim;
    }

}
