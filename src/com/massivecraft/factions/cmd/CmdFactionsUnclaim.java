package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColls;
import com.massivecraft.factions.FPerm;
import com.massivecraft.factions.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.ps.PS;

public class CmdFactionsUnclaim extends FCommand
{
	public CmdFactionsUnclaim()
	{
		this.addAliases("unclaim", "declaim");
		
		this.addRequirements(ReqHasPerm.get(Perm.UNCLAIM.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		// Args
		PS chunk = PS.valueOf(me).getChunk(true);
		Faction newFaction = FactionColls.get().get(me).getNone();
		
		// FPerm
		if (!FPerm.TERRITORY.has(sender, myFaction, true)) return;

		// Apply
		if (fme.tryClaim(newFaction, chunk, true, true)) return;
		
		// Inform
		// TODO: Move the logging stuff into the try-method
		/*myFaction.msg("%s<i> unclaimed some land.", fme.describeTo(myFaction, true));

		if (MConf.get().logLandUnclaims)
		{
			Factions.get().log(fme.getName()+" unclaimed land at ("+chunk.getChunkX()+","+chunk.getChunkZ()+") from the faction: "+oldFaction.getTag());
		}*/
	}
	
}
