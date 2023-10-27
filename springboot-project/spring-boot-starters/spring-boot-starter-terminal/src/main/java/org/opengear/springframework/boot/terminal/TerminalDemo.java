package org.opengear.springframework.boot.terminal;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;

public class TerminalDemo {

    public static void main(String[] args) throws Exception {
//        listFolderStructure("swa-c", "pass", "127.0.0.1", 22, "echo 'hello'");
        localshell();
    }

    public static void localshell() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(null, "127.0.0.1", 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Channel channel = session.openChannel("exec");
        channel.setInputStream(System.in);
        channel.setOutputStream(System.out);
        channel.connect();
        while (!channel.isClosed()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        channel.disconnect();
        session.disconnect();
    }


    public static void listFolderStructure(String username, String password,
                                           String host, int port, String command) throws Exception {

        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("shell");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
