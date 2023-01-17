package fr.zeyx.slowclient.client.gui;

import fr.zeyx.slowclient.client.gui.option.Option;
import fr.zeyx.slowclient.client.gui.option.OptionBuilder;
import fr.zeyx.slowclient.client.gui.option.OptionPage;
import fr.zeyx.slowclient.client.gui.option.control.CheckboxControl;
import fr.zeyx.slowclient.client.gui.option.control.Control;
import fr.zeyx.slowclient.client.gui.option.control.ControlElement;
import fr.zeyx.slowclient.client.gui.option.control.SliderControl;
import fr.zeyx.slowclient.client.gui.widget.FlatButtonWidget;
import fr.zeyx.slowclient.util.Dim2i;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SlowClientOptions extends Screen {

    private final List<OptionPage> pages = new ArrayList<>();
    private final List<Control<?>> controls = new ArrayList<>();
    private final Screen prevScreen;
    private OptionPage currentPage;
    private ControlElement<?> hoveredElement;

    private FlatButtonWidget closeButton;

    public SlowClientOptions(Screen prevScreen) {
        super(Text.translatable("SlowClient Options"));
        this.prevScreen = prevScreen;
    }

    @Override
    protected void init() {
        super.init();
        build();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void build() {

        Option<Boolean> fullbright = OptionBuilder.createBuilder(boolean.class)
                .setName(Text.literal("FullBright"))
                .setTooltip(Text.literal("Enable max gamma!"))
                .setControl(CheckboxControl::new)
                .setDefaultValue(true)
                .setAvailable(true)
                .build();

        Option<Integer> test = OptionBuilder.createBuilder(int.class)
                .setName(Text.literal(""))
                .setTooltip(Text.literal("Enable max gamma!"))
                .setControl(option -> new SliderControl(option, 0, 100, 1))
                .setDefaultValue(50)
                .setAvailable(true)
                .build();

        System.out.println("hey");

        int xMid = this.width / 2;
        int yMid = this.height / 2;

        this.closeButton = new FlatButtonWidget(new Dim2i(this.width - 18, this.height - (this.height - 5), 13, 13), Text.translatable("x"), this::close);

        ControlElement<?> elementfb = fullbright.getControl().createElement(new Dim2i(xMid - 100, yMid,200,18));
        ControlElement<?> elementt = test.getControl().createElement(new Dim2i(xMid - 100, yMid + 18,200,18));
        this.addDrawableChild(elementfb);
        this.addDrawableChild(elementt);
        this.addDrawableChild(this.closeButton);
    }

}
