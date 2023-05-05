package io.github.kloping.mywebsite.hangb

import io.github.kloping.mywebsite.broadcast.LoginStateBroadcast
import io.github.kloping.mywebsite.hangb.entity.VerifyQ
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.command.ConsoleCommandSender
import net.mamoe.mirai.console.command.descriptor.ExperimentalCommandDescriptors
import net.mamoe.mirai.console.command.executeCommand
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
object MiraiConsoleStarter {
    const val path = "work";

    @OptIn(ConsoleExperimentalApi::class, ConsoleTerminalExperimentalApi::class)
    @JvmStatic
    fun main(args: Array<String>) {
        File(path).mkdirs()
        parse(args, true)
        startAsDaemon(
            MiraiConsoleImplementationTerminal(Paths.get("work"))
        )
        HangBotStarter.CDL.countDown()
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

    @OptIn(ExperimentalCommandDescriptors::class)
    @JvmStatic
    fun loginByPwd(vq: VerifyQ) {
        runBlocking {
            val result = ConsoleCommandSender.executeCommand("/login ${vq.qid} ${vq.pwd} ${vq.protocol}", false)
            val qid = result.resolvedCall?.resolvedValueArguments?.get(0)?.value
            if (result.exception != null) {
                LoginStateBroadcast.INSTANCE.broadcast(vq.qid, null, -1, "")
                System.err.println("${qid}登录失败")
            } else {
                LoginStateBroadcast.INSTANCE.broadcast(vq.qid, null, 1, "")
                println("${qid}登录成功")
            }
        }
    }

    @OptIn(ExperimentalCommandDescriptors::class)
    @JvmStatic
    fun loginByQr(vq: VerifyQ) {
        runBlocking {
            ConsoleCommandSender.executeCommand("/qrlogin ${vq.qid} ${vq.protocol}", false)
        }
    }
}