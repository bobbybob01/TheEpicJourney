package me.taylory5.theepicjourney.other;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
//import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum ParticleEffect {
		
		HUGE_EXPLOSION("hugeexplosion"),
	    LARGE_EXPLODE("largeexplode"),
	    FIREWORKS_SPARK("fireworksSpark"),
	    BUBBLE("bubble"),
	    SUSPEND("suspend"),
	    DEPTH_SUSPEND("depthSuspend"),
	    TOWN_AURA("townaura"),
	    CRIT("crit"),
	    MAGIC_CRIT("magicCrit"),
	    MOB_SPELL("mobSpell"),
	    MOB_SPELL_AMBIENT("mobSpellAmbient"),
	    SPELL("spell"),
	    INSTANT_SPELL("instantSpell"),
	    WITCH_MAGIC("witchMagic"),
	    NOTE("note"),
	    PORTAL("portal"),
	    ENCHANTMENT_TABLE("enchantmenttable"),
	    EXPLODE("explode"),
	    FLAME("flame"),
	    LAVA("lava"),
	    FOOTSTEP("footstep"),
	    SPLASH("splash"),
	    LARGE_SMOKE("largesmoke"),
	    CLOUD("cloud"),
	    RED_DUST("reddust"),
	    SNOWBALL_POOF("snowballpoof"),
	    DRIP_WATER("dripWater"),
	    DRIP_LAVA("dripLava"),
	    SNOW_SHOVEL("snowshovel"),
	    SLIME("slime"),
	    HEART("heart"),
	    ANGRY_VILLAGER("angryVillager"),
	    HAPPY_VILLAGER("happyVillager"),
	    ICONCRACK("iconcrack_"),
	    TILECRACK("tilecrack_"),
	    BLOCKDUST("blockdust_");
	   
	    private String particleName;
	    //private static Random random = new Random();
	   
	    ParticleEffect(String particleName) {
	        this.particleName = particleName;
	    }
	   
	    public String getName() {
	        return particleName;
	    }
	   
	    private static Object getEntityPlayer(Player p) throws Exception {
	        return p.getClass().getMethod("getHandle").invoke(p);
	    }
	 
	    private static String getPackageName() {
	        return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	    }
	 
	    private static Object getParticlePacket() throws Exception {
	        Class<?> packet = Class.forName(getPackageName() + ".PacketPlayOutWorldParticles");
	        return packet.getConstructors()[0].newInstance();
	    }
	   
	    private static Class<?> getPacketClass() throws Exception {
	        return Class.forName(getPackageName() + ".Packet");
	    }
	   
	    public static Object getPacket(String particleName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        try {
	                Object packet = getParticlePacket();
	                setField(packet, "a", particleName);
	                setField(packet, "b", (float) location.getX());
	                setField(packet, "c", (float) location.getY());
	                setField(packet, "d", (float) location.getZ());
	                setField(packet, "e", offsetX);
	                setField(packet, "f", offsetY);
	                setField(packet, "g", offsetZ);
	                setField(packet, "h", speed);
	                setField(packet, "i", amount);
	                return packet;
	        } catch(Exception e) {
	                return null;
	        }
	    }
	 
	    public static void sendParticle(Player player, String particleName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        try {
	                Object packet = getPacket(particleName, location, offsetX, offsetY, offsetZ, speed, amount);
	               
	                Object eplayer = getEntityPlayer(player);
	                Field playerConnectionField = eplayer.getClass().getField("playerConnection");
	                Object playerConnection = playerConnectionField.get(eplayer);
	                playerConnection.getClass().getMethod("sendPacket").invoke(playerConnection, packet);
	        } catch(Exception e) {
	                e.printStackTrace();
	        }
	    }
	   
	    public static void sendParticle(Player player, ParticleEffect particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        sendParticle(player, particle.particleName, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static void sendIconCrack(Player player, String iconName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        sendParticle(player, "iconcrack_"+iconName, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static void sendTileCrack(Player player, String tileName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        sendParticle(player, "tilecrack_"+tileName, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static void sendParticleToAll(ParticleEffect particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        sendParticleToAll(particle.getName(), location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
/*	    public static void sendParticleNearby(String particleName, Location location,
	                float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        double f = (random.nextDouble() - 0.5D) * 2.0D;
	        double d3 = f > 1.0F ? (double) (16.0F * f) : 16.0D;
	       
	        try {
	                Object ws = location.getWorld().getClass().getMethod("getHandle").invoke(location.getWorld());
	                Object mcs = ws.getClass().getMethod("getMinecraftServer").invoke(ws);
	                Object plist = mcs.getClass().getMethod("getPlayerList").invoke(mcs);
	                int dimension = (int) getField(ws, "dimension");
	               
	                Method m = plist.getClass().getMethod("sendPacketNearby", getPacketClass());
	                m.invoke(plist, (Object) location.getX(), (Object) location.getY(),
	                        (Object) location.getZ(), (Object) d3, (Object) dimension,
	                        getPacket(particleName, location, offsetX, offsetY, offsetZ,
	                                        speed, amount));
	        } catch(Exception e) {
	                e.printStackTrace();
	        }
	    }*/
	   
	    public static void sendParticleToAll(String particleName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        try {
	                Object ws = location.getWorld().getClass().getMethod("getHandle").invoke(location.getWorld());
	                Object mcs = ws.getClass().getMethod("getMinecraftServer").invoke(ws);
	                Object plist = mcs.getClass().getMethod("getPlayerList").invoke(mcs);
	               
	                Method m = plist.getClass().getMethod("sendAll", getPacketClass());
	               
	                m.invoke(plist, getPacket(particleName, location, offsetX, offsetY, offsetZ,
	                                        speed, amount));
	        } catch(Exception e) {
	                e.printStackTrace();
	        }
	    }
	   
	    public static void sendIconCrackToAll(int id, Integer data, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        String s = (data == null) ? "iconcrack_"+id : "iconcrack_"+id+"_" + data;
	        sendParticleToAll(s, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static void sendTileCrackToAll(int id, Integer data, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        String s = (data == null) ? "tilecrack_"+id : "tilecrack_"+id+"_" + data;
	        sendParticleToAll(s, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static void sendBlockDustToAll(int id, Integer data, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
	        String s = (data == null) ? "blockdust_"+id : "blockdust_"+id+"_" + data;
	        sendParticleToAll(s, location, offsetX, offsetY, offsetZ, speed, amount);
	    }
	   
	    public static ParticleEffect get(String name) {
	        for (ParticleEffect e : ParticleEffect.values()) {
	                if (e.getName().equalsIgnoreCase(name)) {
	                        return e;
	                }
	        }
	        return null;
	    }
	   
	    /**
	     * Sets a value of an {@link Object} via reflection
	     *
	     * @param instance Instance the class to use
	     * @param fieldName The name of the {@link Field} to modify
	     * @param value The value to set
	     *
	     * @throws SecurityException
	     * @throws NoSuchFieldException
	     * @throws IllegalAccessException
	     * @throws IllegalArgumentException
	     */
	    public static void setField(Object instance, String fieldName, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	        Field field = instance.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        field.set(instance, value);
	    }
	 
	    /**
	     * Get a value of an {@link Object}'s {@link Field}
	     *
	     * @param instance The target {@link Object}
	     * @param fieldName Name of the {@link Field}
	     * @return The value of {@link Object} instance's {@link Field} with the name of fieldName
	     *
	     * @throws SecurityException
	     * @throws NoSuchFieldException
	     * @throws IllegalAccessException
	     * @throws IllegalArgumentException
	     */
	    public static Object getField(Object instance, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	        Field field = instance.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        return field.get(instance);
	    }
}
