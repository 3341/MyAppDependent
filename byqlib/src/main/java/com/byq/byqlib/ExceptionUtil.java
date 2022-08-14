package com.byq.byqlib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ExceptionUtil {
    public static String getExceptionTraceContent(Exception e) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bo);
        e.printStackTrace(printStream);
        String s = bo.toString();
        printStream.close();
        try {
            bo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return s;
    }
}
