package me.kzlyth.hidePlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import me.kzlyth.hidePlugin.Commands.HideCommand;

public final class HidePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        logStartupInfo();
        getCommand("hide").setExecutor(new HideCommand());
    }

    private void logStartupInfo() {
        String separator = ChatColor.GRAY + "================================";

        Bukkit.getConsoleSender().sendMessage(separator);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "         HidePlugin - Startup Info");
        Bukkit.getConsoleSender().sendMessage(separator);
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Plugin Version: " + ChatColor.WHITE + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Developer: " + ChatColor.WHITE + "Kzlyth");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Discord: " + ChatColor.AQUA + "https://discord.gg/jyrjcrGMkw");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Server Version: " + ChatColor.WHITE + Bukkit.getVersion());
        Bukkit.getConsoleSender().sendMessage(separator);
    }

    @Override
    public void onDisable() {
        String separator = ChatColor.GRAY + "================================";

        Bukkit.getConsoleSender().sendMessage(separator);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "  HidePlugin got deactivated!");
        Bukkit.getConsoleSender().sendMessage(separator);
    }
}