package me.kzlyth.hidePlugin.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Random;
import java.util.HashSet;
import java.util.UUID;

public class HideCommand implements CommandExecutor {
    private final HashSet<UUID> hiddenPlayers = new HashSet<>();

    private String generateRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder random = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 4; i++) {
            random.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return random.toString();
    }

    private void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis Command can only be executed from Players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("hide.use")) {
            sendActionBar(player, "§cYou don't have permission to execute this Command.");
            return true;
        }

        if (hiddenPlayers.contains(player.getUniqueId())) {
            hiddenPlayers.remove(player.getUniqueId());
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.showPlayer(player);
            }

            sendActionBar(player, "§aYou're not hidden anymore!");
        } else {
            hiddenPlayers.add(player.getUniqueId());
            String randomName = "§k" + generateRandomString();

            player.setDisplayName(randomName);
            player.setPlayerListName(randomName);

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.hidePlayer(player);
            }

            sendActionBar(player, "§aYou're now hidden!");
        }

        return true;
    }
}
