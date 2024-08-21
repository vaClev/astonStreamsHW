import Trading.PuttingIntoPractice;
import Trading.Trader;
import Trading.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tests {
    public static void runAllTests() {
        PuttingIntoPractice.main();
        showTransactionsIn2011();
        showTradersCities();
        showCambridgeTraders();
        showTraderNamesSortedABC();
        System.out.println(isTraderFrom("milan")+"\n\n");
        showCambridgeTransactionsSums();
        showMaxTransactionValue();
        System.out.println(getTransactionWithMinValue());
    }
    private static void showTransactionsIn2011() {
        //Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        System.out.println("task 1");
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        transactionsStream
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println("task 1 Completed!\n\n");
    }
    private static void showTradersCities() {
        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("task 2");
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        transactionsStream
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("task 2 Completed!\n\n");
    }
    private static void showCambridgeTraders() {
        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("task 3");
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        transactionsStream
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("task 3 Completed!\n\n");
    }
    private static void showTraderNamesSortedABC() {
        //Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println("task 4");
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        String traderNames = Arrays.toString(
                transactionsStream
                        .map(Transaction::getTrader)
                        .map(Trader::getName)
                        .distinct()
                        .sorted()
                        .toArray(String[]::new));
        traderNames = traderNames.replaceAll("\\pP","");
        System.out.println(traderNames);
        System.out.println("task 4 Completed!\n\n");
    }
    private static boolean isTraderFrom(String city) {
        //5. Выяснить, существует ли хоть один трейдер из Милана.
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        return transactionsStream
                .map(Transaction::getTrader)
                .anyMatch(trader->trader.getCity().equalsIgnoreCase(city));
    }
    private static void showCambridgeTransactionsSums () {
        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        transactionsStream
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("task 6 Completed!\n\n");
    }
    private static void showMaxTransactionValue() {
        //7. Какова максимальная сумма среди всех транзакций?
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        int maxTransactionValue= transactionsStream
                .map(Transaction::getValue)
                .max(Integer::compare).get();
        System.out.println("Max transaction value: "+maxTransactionValue);
        System.out.println("task 7 Completed!\n\n");
    }

    public static Transaction getTransactionWithMinValue() {
        //8. Найти транзакцию с минимальной суммой.
        Stream<Transaction> transactionsStream = PuttingIntoPractice.transactions.stream();
        Optional<Transaction> minTransaction = transactionsStream.min(Comparator.comparingInt(Transaction::getValue));
        return minTransaction.orElse(new Transaction(new Trader("none","none"),0,-1));
    }

}
