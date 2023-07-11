package io.github.kloping.mywebsite.hangb;

import io.github.kloping.common.Public;
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;
import net.mamoe.mirai.event.GlobalEventChannel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author github.kloping
 */
public class MiraiStarter {
    public static final File FILE_PID = new File("./pid");

    public static void main(String[] args) throws IOException {
//        new File(args[0], args[1]).mkdirs();
//        Public.EXECUTOR_SERVICE.submit(() -> {
//            MiraiConsoleImplementationTerminal terminal =
//                    new MiraiConsoleImplementationTerminal(Paths.get(args[0], args[1]));
//            MiraiConsoleTerminalLoader.INSTANCE.startAsDaemon(terminal);
//        });
//        GlobalEventChannel.INSTANCE.registerListenerHost(new DefaultHandler());
    }
}
