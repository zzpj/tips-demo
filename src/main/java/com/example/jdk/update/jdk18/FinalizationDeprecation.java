package com.example.jdk.update.jdk18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FinalizationDeprecation {

    FileInputStream fis = null;

    public FinalizationDeprecation() throws FileNotFoundException {
        this.fis = new FileInputStream("file.txt");
    }

    /*
    @Override
    protected void finalize() throws Throwable {
        fis.close();
    }
    //*/
}
