package millaav.quantumupgrade.common.network;

import net.minecraft.nbt.NBTTagCompound;

public interface IReceiveServerEvents {
    void onServerEvent(int var1, NBTTagCompound var2);
}