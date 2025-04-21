package ru.elizarov;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class Stock {
    @Getter
    private final String name;
    private final List<StockSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(StockSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void updatePrice(double newPrice) {
        subscribers.forEach(subscriber ->
                subscriber.onPriceUpdate(name, newPrice));
    }
}
