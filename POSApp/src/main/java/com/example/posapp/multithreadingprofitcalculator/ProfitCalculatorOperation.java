package com.example.posapp.multithreadingprofitcalculator;

import com.example.posapp.models.SalesOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class ProfitCalculatorOperation {

    public static void calculate(List<SalesOrder> allSales) throws InterruptedException {
        if (allSales == null || allSales.isEmpty()) {
            ProfitCalculator.reset();
            return;
        }

        int mid = allSales.size() / 2;

        List<SalesOrder> firstHalf  = allSales.subList(0, mid);
        List<SalesOrder> secondHalf = allSales.subList(mid, allSales.size());

        Semaphore semaphore = new Semaphore(1);
        ProfitCalculator.reset();


        CalSemaThread t1 = new CalSemaThread(semaphore, firstHalf);
        CalSemaThread t2 = new CalSemaThread(semaphore, secondHalf);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
