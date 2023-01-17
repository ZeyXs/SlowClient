package fr.zeyx.slowclient.mixin;

import fr.zeyx.slowclient.SlowClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Mixin(ChatHud.class)
public class ReceiveMessageMixin {

    // TODO: ONLY DETECT CHAT MESSAGE SEND BY PLAYERS OR OTHERS

    @ModifyArg(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V"), index = 0)
    public Text addMessage(Text message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.now();
        return Text.of(Formatting.GRAY + "[" + dtf.format(localTime) + "] " + Formatting.RESET + message.getString());
    }
}
