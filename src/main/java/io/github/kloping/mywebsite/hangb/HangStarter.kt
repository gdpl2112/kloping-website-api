package io.github.kloping.mywebsite.hangb

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.terminal.ConsoleTerminalExperimentalApi
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader.parse
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader.startAsDaemon
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import java.io.File
import java.nio.file.Paths

/**
 * @author github.kloping
 */
object HangStarter {
    const val path = "work";

    @OptIn(ConsoleExperimentalApi::class, ConsoleTerminalExperimentalApi::class)
    @JvmStatic
    fun main(args: Array<String>) {
        File(path).mkdirs()
        parse(args, true)
        startAsDaemon(
            MiraiConsoleImplementationTerminal(Paths.get("work"))
        )
        try {
            runBlocking {
                MiraiConsole.job.invokeOnCompletion { err ->
                    if (err != null) {
                        Thread.sleep(1000)
                    }
                }
                MiraiConsole.job.join()
            }
        } catch (e: CancellationException) {
            e.printStackTrace()
        }
    }
}