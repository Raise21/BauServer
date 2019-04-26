package de.daniel.bauserver.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    public ItemStack createItemStack( final Material material, final int id, final String displayName, final int amount, final String... lore ) {
        final ItemStack itemStack = new ItemStack( material, amount, (short) id );

        final ItemMeta meta = itemStack.getItemMeta( );

        meta.setLore( Arrays.asList( lore ) );
        meta.setDisplayName( displayName );
        itemStack.setItemMeta( meta );

        return itemStack;
    }

    public ItemStack createItemStack( final Material material, final int id, final String displayName, final String[] lore) {
        final ItemStack itemStack = new ItemStack( material );
        itemStack.setDurability( ( short ) id );
        final ItemMeta meta = itemStack.getItemMeta( );
        meta.setLore( Arrays.asList( lore ) );
        meta.setDisplayName( displayName );
        itemStack.setItemMeta( meta );
        return itemStack;
    }

}
