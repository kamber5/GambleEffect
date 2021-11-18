package com.serbcraft.gambleeffect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class GambleEffect extends JavaPlugin {
	
	Random rand = new Random();
	
	public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {    	
    	Player p = (Player) sender;
    	String[] pocetak = args;
    	if (pocetak.length == 0) {
    		p.sendMessage(ChatColor.GOLD + "/gambleeffect gamble - /gambleeffect (efekat) (trajanje)");
    		return true;
    	}
    	
		// /gambleeffect gamble - logic
    	// int chance = rand.nextInt(30);
    	String firstArg = args[0];
    	if (firstArg.equalsIgnoreCase("gamble")) {
			int randPotion = rand.nextInt(PotionEffectType.values().length);
			PotionEffectType type = PotionEffectType.values()[randPotion];
			p.addPotionEffect(new PotionEffect(type, 200, 1));
			p.sendMessage(ChatColor.GOLD + "Dobili ste " + type.getName() + " efekat u trajanju od 20 sekundi!");
			return true;
    	}

		// /gambleeffect (efekat) (trajanje)
		// Provjera za potion type
		PotionEffectType type = PotionEffectType.getByName(firstArg);
		if (type == null) {
            sender.sendMessage(ChatColor.RED + "Ne postoji takav efekat! /gambleeffect (efekat) (trajanje)");
            return true;
        }

		if (args.length == 2) {
			// Pretvaranje broja u arg stringu na integer
			int duration=Integer.parseInt(args[1]) * 20;

			if (type != null && duration > 0) {
				p.addPotionEffect(new PotionEffect(type, duration, 1));
				p.sendMessage(ChatColor.GOLD + "Dobili ste " + type.getName() + " efekat u trajanju od " + duration/20 + " sekundi!");
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "Netacno unesena komanda. /gambleeffect (efekat) (trajanje)");
			}
		} else {
			p.sendMessage(ChatColor.RED + "Netacno unesena komanda. /gambleeffect (efekat) (trajanje)");
		}

	 return true;
    }
	

}