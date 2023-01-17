package fr.zeyx.slowclient.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

public class ListenerManager<T extends Event> {

    private final Queue<Consumer<T>> listenerQueue = new ConcurrentLinkedDeque<>();

    public void fire(T event) {
        for (var listener : listenerQueue) listener.accept(event);
    }

    public void register(Consumer<T> listener) {
        listenerQueue.offer(listener);
    }

}
