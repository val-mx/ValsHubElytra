package valmx.spigot.hubelytra.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MoveListener implements Listener {


    public MoveListener() {

        lastItems = new HashMap<>();

    }

    public static ItemStack getLastItem(Player p) {
        ItemStack val = lastItems.get(p);
        lastItems.remove(p);
        return val;
    }

    private static HashMap<Player,ItemStack> lastItems;

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("hubelytra.allow")) {
            if (!(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals(Material.ELYTRA))) {

                Location playerLoc = p.getLocation();
                Location worldBlockLoc = playerLoc;
                int y = playerLoc.getBlockY();
                for (int i = 0; i < 4; i++) {
                    if (!worldBlockLoc.getBlock().getType().equals(Material.AIR)) {
                        return;
                    }
                    y--;
                    worldBlockLoc.setY(y);
                }
                lastItems.put(p,p.getInventory().getChestplate());
                p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
            } else {
                Location playerLoc = p.getLocation();
                int y = playerLoc.getBlockY();
                y--;
                playerLoc.setY(y);

                if(!playerLoc.getBlock().getType().equals(Material.AIR))
                    p.getInventory().setChestplate(getLastItem(p));

            }
        }

    }

}
