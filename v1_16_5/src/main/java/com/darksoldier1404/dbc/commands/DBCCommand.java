package com.darksoldier1404.dbc.commands;

import com.darksoldier1404.dbc.BubbleChat;
import com.darksoldier1404.dppc.utils.DataContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class DBCCommand implements CommandExecutor, TabCompleter {
    private final DataContainer data = BubbleChat.data;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(data.getPrefix() + "권한이 없습니다.");
            return false;
        }
        if(args.length == 1) {
            sender.sendMessage(data.getPrefix() + "사용법: /dbc reload/rl - 콘피그 설정을 리로드 합니다.");
            return false;
        }
        if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
            data.reload();
            return false;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            return Arrays.asList("reload", "rl");
        }
        return null;
    }
}
