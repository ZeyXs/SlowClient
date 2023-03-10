package fr.zeyx.slowclient.client.gui.option.control;

import fr.zeyx.slowclient.client.gui.option.Option;
import fr.zeyx.slowclient.util.Dim2i;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Rect2i;

public class CheckboxControl implements Control<Boolean> {

    private final Option<Boolean> option;

    public CheckboxControl(Option<Boolean> option) {
        this.option = option;
    }

    @Override
    public ControlElement<Boolean> createElement(Dim2i dim) {
        return new CheckboxControlElement(this.option, dim);
    }

    @Override
    public Option<Boolean> getOption() {
        return this.option;
    }

    @Override
    public int getMaxWidth() {
        return 30;
    }

    private static class CheckboxControlElement extends ControlElement<Boolean> {

        private final Rect2i button;

        public CheckboxControlElement(Option<Boolean> option, Dim2i dim) {
            super(option, dim);
            this.button = new Rect2i(dim.getLimitX() - 16, dim.getCenterY() - 5, 10, 10);
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);

            final int x = this.button.getX();
            final int y = this.button.getY();
            final int w = x + this.button.getWidth();
            final int h = y + this.button.getHeight();

            final boolean enabled = this.option.isAvailable();
            final boolean ticked = enabled && this.option.getValue();

            final int color;

            if (enabled) {
                color = ticked ? 0xFF94E4D3 : 0xFFFFFFFF;
            } else {
                color = 0xFFAAAAAA;
            }

            if (ticked) {
                this.drawRect(x + 2, y + 2, w - 2, h - 2, color);
            }

            this.drawRectOutline(x, y, w, h, color);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (this.option.isAvailable() && button == 0 && this.dim.containsCursor(mouseX, mouseY)) {
                this.option.setValue(!this.option.getValue());
                this.playClickSound();
                return true;
            }
            return false;
        }

        protected void drawRectOutline(int x, int y, int w, int h, int color) {
            final float a = (float) (color >> 24 & 255) / 255.0F;
            final float r = (float) (color >> 16 & 255) / 255.0F;
            final float g = (float) (color >> 8 & 255) / 255.0F;
            final float b = (float) (color & 255) / 255.0F;

            this.drawQuads(vertices -> {
                addQuad(vertices, x, y, w, y + 1, a, r, g, b);
                addQuad(vertices, x, h - 1, w, h, a, r, g, b);
                addQuad(vertices, x, y, x + 1, h, a, r, g, b);
                addQuad(vertices, w - 1, y, w, h, a, r, g, b);
            });
        }
    }

}
