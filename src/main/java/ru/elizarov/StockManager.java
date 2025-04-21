package ru.elizarov;

import java.util.ArrayList;
import java.util.List;

class StockManager {
    private final List<Stock> stocks = new ArrayList<>();

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void updateStockPrice(String stockName, double price) {
        stocks.stream()
                .filter(stock -> stock.getName().equals(stockName))
                .findFirst()
                .ifPresent(stock -> stock.updatePrice(price));
    }
}
