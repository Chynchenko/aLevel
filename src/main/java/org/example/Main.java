package org.example;

import util.Concurrency;
import util.CreateAndStartThreads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CreateAndStartThreads.createAndStartThreads();
        Concurrency.main(args);
    }
}