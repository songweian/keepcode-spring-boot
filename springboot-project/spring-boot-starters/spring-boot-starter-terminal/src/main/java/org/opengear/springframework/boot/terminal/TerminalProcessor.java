package org.opengear.springframework.boot.terminal;

import java.io.IOException;

public class TerminalProcessor {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("bash");
        pb.redirectErrorStream(true);
        Process process = pb.start();
        // read from system in
        new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bufferW = new byte[1024];
                    int bytesWrite;
                    while (true) {
                        if ((bytesWrite = System.in.read(bufferW)) == -1) {
                            Thread.sleep(1000);
                        }
                        boolean alive = process.isAlive();
                        System.out.println("is alive" + alive);
                        process.getOutputStream().write(bufferW, 0, bytesWrite);
                        process.getOutputStream().flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        process.getOutputStream().write("ls -l\n".getBytes());
//        process.getOutputStream().flush();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while (true) {
            if ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                Thread.sleep(1000);
            }
            System.out.write(buffer, 0, bytesRead);
        }
//        process.destroy();

    }
}
