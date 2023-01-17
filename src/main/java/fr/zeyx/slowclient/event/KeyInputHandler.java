package fr.zeyx.slowclient.event;

import fr.zeyx.slowclient.client.gui.SlowClientOptions;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyInputHandler {

    public static final String KEY_CATEGORY = "key.category.slowclient";
    public static final String KEY_OPEN_MENU = "key.slowclient.open_menu";
    public static final String KEY_FREELOOK = "key.slowclient.freelook";

    public static KeyBinding openMenuKey;
    public static KeyBinding freelookKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (openMenuKey.wasPressed()) {
                if (client.currentScreen instanceof SlowClientOptions) {
                    client.setScreen(null);
                } else {
                    client.setScreen(new SlowClientOptions(null));
                }
            }
        });
    }

    public static void register() {
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_OPEN_MENU, InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_N, KEY_CATEGORY));
        freelookKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_FREELOOK, InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_J, KEY_CATEGORY));

        registerKeyInputs();
    }

}
