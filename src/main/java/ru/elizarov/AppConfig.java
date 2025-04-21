package ru.elizarov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {

    // 9.1.1 Hello world
    @Bean
    public String helloBean() {
        return "Hello world";
    }

    // 9.1.2 random
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public int randomValue() {
        return random().nextInt(100); // от 0 до 99
    }

    // 9.1.3 дата первого обращения
    private Date firstAccess = new Date();

    @Bean
    public Date startDateBean() {
        return firstAccess;
    }

    // 9.1.4 предикат
    @Bean
    public Predicate<Integer> rangePredicate() {
        return x -> x >= 2 && x <= 5;
    }

    // 9.1.5 минимакс
    @Bean(name = "min")
    public int min() {
        return 1;
    }

    @Bean(name = "max")
    public int max() {
        return 10;
    }

    // 9.2. генератор рендом чисел
    @Bean
    public UniqueRandomGenerator uniqueRandomGenerator() {
        return new UniqueRandomGenerator(min(), max());
    }

    @Bean
    @Scope("prototype")
    public int randomValue2() {
        return uniqueRandomGenerator().next();
    }

    // 9.2.2 Отзывы
    @Bean
    public Review review1() {
        return new Review("Очень хорошо", 4);
    }

    @Bean
    public Review review2() {
        return new Review("Сойдет", 3);
    }

    @Bean
    public Review review3() {
        return new Review("Сложно сказать", randomValue2());
    }

    // 9.2.3 бест отзыв
    @Bean
    public Review bestReview() {
        Review r1 = review1();
        Review r2 = review2();
        Review r3 = review3();
        return Collections.max(List.of(r1, r2, r3),
                Comparator.comparingInt(Review::getRating));
    }

    // 9.2.4 студы
    @Bean
    public Student student1() {
        Student student = new Student("Иван", rangePredicate());
        student.addGrade(3);
        student.addGrade(4);
        return student;
    }

    @Bean
    public Student student2() {
        Student student = new Student("Мария", rangePredicate());
        student.addGrade(2);
        student.addGrade(5);
        return student;
    }

    // 9.2.5 билдер студентов
    @Bean
    public StudentBuilder studentBuilder() {
        return new StudentBuilder(rangePredicate());
    }

    // 9.2.6 стриминг платформа
    @Bean
    public DataReader dataReader() {
        return new FileDataReader();
    }

    @Bean
    public DataWriter dataWriter() {
        return new FileDataWriter();
    }

    @Bean(name = "toUpperCase")
    public Function<String, String> toUpperCase() {
        return String::toUpperCase;
    }

    @Bean(name = "removeA")
    public Function<String, String> removeA() {
        return s -> s.replaceAll("A", "");
    }

    @Bean(name = "filterLongWords")
    public Function<String, String> filterLongWords() {
        return s -> String.join(" ",
                Arrays.stream(s.split("\\s+"))
                        .filter(word -> word.length() > 4)
                        .collect(Collectors.toList()));
    }

    @Bean
    public DataProcessor dataProcessor() {
        List<Function<String, String>> transformations = List.of(
                toUpperCase(), removeA(), filterLongWords()
        );
        return new DataProcessor(dataReader(), dataWriter(), transformations);
    }

    // 9.2.7 светофор
    @Bean
    public TrafficLight trafficLight() {
        return new ThreeColorTrafficLight();
    }

    // 9.2.8 оповещения
    @Bean
    public StockSubscriber subscriber1() {
        return new StockSubscriber() {
            @Override
            public void onPriceUpdate(String stockName, double newPrice) {
                System.out.println("Подписчик 1: " + stockName + " = " + newPrice);
            }
        };
    }

    @Bean
    public StockSubscriber subscriber2() {
        return new StockSubscriber() {
            @Override
            public void onPriceUpdate(String stockName, double newPrice) {
                System.out.println("Подписчик 2: " + stockName + " = " + newPrice);
            }
        };
    }

    @Bean
    public StockManager stockManager() {
        StockManager manager = new StockManager();
        Stock stock1 = new Stock("Apple");
        Stock stock2 = new Stock("Google");
        stock1.addSubscriber(subscriber1());
        stock2.addSubscriber(subscriber2());
        manager.addStock(stock1);
        manager.addStock(stock2);
        return manager;
    }
}


