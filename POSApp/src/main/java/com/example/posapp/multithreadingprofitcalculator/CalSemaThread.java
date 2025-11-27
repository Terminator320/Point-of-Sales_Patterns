package com.example.posapp.multithreadingprofitcalculator;

import com.example.posapp.LogConfig;
import com.example.posapp.models.SalesOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalSemaThread extends Thread{
    private static final Logger LOGGER = LogConfig.getLogger(CalSemaThread.class.getName());

    Semaphore semaphore;
    private final List<SalesOrder> ordersToProcess;


    public CalSemaThread(Semaphore semaphore, List<SalesOrder> ordersToProcess) {
        this.semaphore = semaphore;
        this.ordersToProcess = ordersToProcess;
    }



    @Override
    public void run(){
        //to keep track of totals not from the db yet
        double localTotal = 0.0;
        double localCostTotal = 0.0;

        //going through all the orders that have been processed and getting the subtotal and cost to make
        for (SalesOrder order : ordersToProcess) {
            double saleTotal = order.getSubtotal();
            double saleCost  = order.getTotalCostPrice();

            localTotal     += saleTotal;
            localCostTotal += saleCost;
        }

        //taking the values of them and getting the profit
        double localProfit = localTotal - localCostTotal;

        try
        {
            semaphore.acquire();

            ProfitCalculator.total     += localTotal;
            ProfitCalculator.costTotal += localCostTotal;
            ProfitCalculator.profit    += localProfit;

        } catch (InterruptedException e){
            LOGGER.log(Level.SEVERE, e.getCause() + "Error while multithreading thread");
        }
        finally {
            semaphore.release();
        }

    }
}
