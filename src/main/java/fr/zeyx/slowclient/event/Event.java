package fr.zeyx.slowclient.event;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class Event {

    private final CallbackInfo callbackInfo;

    public Event(CallbackInfo callbackInfo) {
        this.callbackInfo = callbackInfo;
    }

    public CallbackInfo getCallbackInfo() {
        return this.callbackInfo;
    }

}
