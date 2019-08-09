package com.mebank.codechallenge;

import com.mebank.codechallenge.service.CalculatorService;
import com.mebank.codechallenge.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Scanner;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private CalculatorService calculatorService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        printHelp();
        int i = 0;
        while (i != 9) {
            // TODO: Perform input validation using Regex
            System.out.print("AccountId: ");
            String accountId = scanner.nextLine().toUpperCase();
            System.out.print("from: ");
            LocalDateTime from = ServiceUtil.convertStringToLocalDateTime(scanner.nextLine());
            System.out.print("to: ");
            LocalDateTime to = ServiceUtil.convertStringToLocalDateTime(scanner.nextLine());

            System.out.println("====================Result====================");
            System.out.println(calculatorService.calculate(accountId, from, to));

            System.out.println("If you want to exit, Please enter 9");
            System.out.println("If you want to continue, Press any keys Except 9");
            String decision = scanner.nextLine();
            if (decision.equals("9")) {
                i = 9;
            }
        }
    }

    private void printHelp()
    {
        System.out.println("============Sample Data==============");
        System.out.println("AccountId: ACC334455");
        System.out.println("from: 20/10/2018 12:00:00");
        System.out.println("to: 20/10/2018 19:00:00");
        System.out.println("Date Format must be as same as above!");
        System.out.println("============End of Sample============");
    }
}
