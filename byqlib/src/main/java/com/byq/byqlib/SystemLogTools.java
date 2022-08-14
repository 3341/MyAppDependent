package com.byq.byqlib;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Uncompleted
 */
public class SystemLogTools {
    public static OutputStream defaultOut;
    public static PrintStream currentOut;

    public static interface LogReceiver {
        public void onReceivedLog(String content);
    }

    public static class LogBox {

    }

    public static class DefaultLogReceiver implements  LogReceiver {
        @Override
        public void onReceivedLog(String content) {

        }
    }

    private static class MyPrintStream extends PrintStream {

        public MyPrintStream(OutputStream out) {
            super(out);
        }


    }

    public static void resetSystemPrinter(LogReceiver receiver) {

    }
}
