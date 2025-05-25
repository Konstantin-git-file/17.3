package ru.elizarov;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {

    @Bean
    public String helloBean() {
        return "Hello world";
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    @Scope("prototype")
    public int randomValue(Random random) {
        return random.nextInt(100);
    }

    @Bean
    @Scope("prototype")
    public Date startDateBean() {
        return new Date();
    }

    @Bean
    public Predicate<Integer> rangePredicate() {
        return x -> x >= 2 && x <= 5;
    }

    @Bean(name = "min")
    public int min() {
        return 1;
    }

    @Bean(name = "max")
    public int max() {
        return 10;
    }

    @Bean
    public UniqueRandomGenerator uniqueRandomGenerator(int min, int max) {
        return new UniqueRandomGenerator(min, max);
    }

    @Bean
    @Scope("prototype")
    public int randomValue2(UniqueRandomGenerator uniqueRandomGenerator) {
        return uniqueRandomGenerator.next();
    }

    @Bean
    public Review review1() {
        return new Review("Очень хорошо", 4);
    }

    @Bean
    public Review review2() {
        return new Review("Сойдет", 3);
    }

    @Bean
    @Scope("prototype")
    public Review review3(int randomValue2) {
        return new Review("Сложно сказать", randomValue2);
    }

    @Bean
    @Scope("prototype")
    public Review bestReview(ApplicationContext context) {
        Map<String, Review> reviews = context.getBeansOfType(Review.class);
        return Collections.max(reviews.values(), Comparator.comparingInt(Review::getRating));
    }

    @Bean
    public Student student1(Predicate<Integer> rangePredicate) {
        Student student = new Student("Иван", rangePredicate);
        student.addGrade(3);
        student.addGrade(4);
        return student;
    }

    @Bean
    public Student student2(Predicate<Integer> rangePredicate) {
        Student student = new Student("Мария", rangePredicate);
        student.addGrade(2);
        student.addGrade(5);
        return student;
    }

    @Bean
    public StudentBuilder studentBuilder(Predicate<Integer> rangePredicate) {
        return new StudentBuilder(rangePredicate);
    }

    @Bean
    public DataReader dataReader() {
        return new FileDataReader();
    }

    @Bean
    public DataWriter dataWriter() {
        return new FileDataWriter();
    }

    @Bean
    @Qualifier("toUpperCase")
    public Function<String, String> toUpperCaseTransformer() {
        return String::toUpperCase;
    }

    @Bean
    @Qualifier("removeA")
    public Function<String, String> removeATransformer() {
        return s -> s.replaceAll("A", "");
    }

    @Bean
    @Qualifier("filterLongWords")
    public Function<String, String> filterLongWordsTransformer() {
        return s -> Arrays.stream(s.split("\\s+"))
                .filter(word -> word.length() > 4)
                .collect(Collectors.joining(" "));
    }

    @Bean
    public List<Function<String, String>> textTransformations(
            @Qualifier("toUpperCase") Function<String, String> upperCase,
            @Qualifier("removeA") Function<String, String> removeA,
            @Qualifier("filterLongWords") Function<String, String> filterLongWords) {

        return List.of(upperCase, removeA, filterLongWords);
    }

    @Bean
    public DataProcessor dataProcessor(DataReader reader, DataWriter writer, List<Function<String, String>> textTransformations) {
        return new DataProcessor(reader, writer, textTransformations);
    }

    @Bean
    public TrafficLight trafficLight() {
        return new ThreeColorTrafficLight();
    }

    @Bean
    public StockSubscriber subscriber1() {
        return (stockName, newPrice) ->
                System.out.println("Подписчик 1: " + stockName + " = " + newPrice);
    }

    @Bean
    public StockSubscriber subscriber2() {
        return (stockName, newPrice) ->
                System.out.println("Подписчик 2: " + stockName + " = " + newPrice);
    }

    @Bean
    public Stock appleStock(List<StockSubscriber> subscribers) {
        Stock stock = new Stock("Apple");
        subscribers.forEach(stock::addSubscriber);
        return stock;
    }

    @Bean
    public Stock googleStock(List<StockSubscriber> subscribers) {
        Stock stock = new Stock("Google");
        subscribers.forEach(stock::addSubscriber);
        return stock;
    }

    @Bean
    public StockManager stockManager(List<Stock> stocks) {
        StockManager manager = new StockManager();
        stocks.forEach(manager::addStock);
        return manager;
    }
}