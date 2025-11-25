package com.example.posapp.multithreadingprofitcalculator;

import com.example.posapp.models.SalesOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CalSemaThread extends Thread{
    Semaphore semaphore;


    public CalSemaThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        try
        {
            semaphore.acquire();
            List<SalesOrder> s = new ArrayList<>();

            s.add(SalesOrder.getALLSales());

            List<SalesOrder> s1 = s.subList(0,s.size()/2);
            List<SalesOrder> s2 = s.subList(s.size()/2,s.size());



            





        } catch (InterruptedException e){
            e.getMessage();
        }

    }
}
