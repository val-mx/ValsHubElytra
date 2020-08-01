package valmx.spigot.hubelytra;

import org.bukkit.plugin.java.JavaPlugin;
import valmx.spigot.hubelytra.listeners.MoveListener;

public class HubElytra extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new MoveListener(),this);
        super.onEnable();
    }
}
