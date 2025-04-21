package ru.elizarov;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 9.1.1 Hello world
        String hello = context.getBean(String.class);
        System.out.println("9.1.1: " + hello);

        // 9.1.2 Random два раза
        int random1 = context.getBean("randomValue2", Integer.class);
        int random2 = context.getBean("randomValue2", Integer.class);
        System.out.println("9.1.2: " + random1 + ", " + random2);

        // 9.1.3 Start Date
        Date date = context.getBean(Date.class);
        System.out.println("9.1.3: " + date);

        // 9.1.4 Предикат
        Predicate<Integer> range = context.getBean(Predicate.class);
        System.out.println("3 входит? " + range.test(3));
        System.out.println("6 входит? " + range.test(6));

        // 9.1.5 Минимакс
        int min = (int) context.getBean("min");
        int max = (int) context.getBean("max");
        System.out.println("9.1.5: min = " + min + ", max = " + max);

        // 9.2.1 random
        random1 = context.getBean("randomValue2", Integer.class);
        random2 = context.getBean("randomValue2", Integer.class);
        System.out.println("9.2.1: " + random1 + ", " + random2);

        // 9.2.2 отзывы
        Review r1 = context.getBean("review1", Review.class);
        Review r2 = context.getBean("review2", Review.class);
        Review r3 = context.getBean("review3", Review.class);
        System.out.println("9.2.2: " + r1 + ", " + r2 + ", " + r3);

        // 9.2.3 бест отзыв
        Review best = context.getBean("bestReview", Review.class);
        System.out.println("9.2.3: " + best);

        // 9.2.4 студы
        Student s1 = context.getBean("student1", Student.class);
        Student s2 = context.getBean("student2", Student.class);
        System.out.println("9.2.4: " + s1 + ", " + s2);

        // 9.2.5 создание студов
        StudentBuilder builder = context.getBean(StudentBuilder.class);
        Student s = builder.build("Алексей", 3, 4);
        System.out.println("9.2.5: " + s);

        // 9.2.6 Стриминг
        DataProcessor processor = context.getBean(DataProcessor.class);
        processor.process("input.txt", "output.txt");
        System.out.println("9.2.6: Данные обработаны");

        // 9.2.7 настройка светофора
        TrafficLight tf = context.getBean(TrafficLight.class);
        tf.next(); // зеленый
        tf.next(); // желтый
        tf.next(); // красный
        tf.next(); // желтый
        tf.next(); // зеленый
        System.out.println("9.2.7: Светофор протестирован");

        // 9.2.8 задание для оповещений
        StockManager manager = context.getBean(StockManager.class);
        manager.updateStockPrice("Apple", 150.0);
        manager.updateStockPrice("Google", 2800.0);
        System.out.println("9.2.8: Оповещения отправлены");
    }
}