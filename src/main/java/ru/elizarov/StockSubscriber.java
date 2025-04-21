package ru.elizarov;

// 9.2.8 Оповещения
interface StockSubscriber {
    void onPriceUpdate(String stockName, double newPrice);
}
