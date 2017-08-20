package room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import player.Player;

class PlayerState{
	protected long	  player;		// ���id
	protected Integer roomId;		// ����ID
	
	public PlayerState( long player, Integer room) {
		this.player = player;
		this.roomId = roomId;
	}
}

public class RoomMgr {
	protected static RoomMgr inst = null;
	public static HashMap<Integer, Room>  rooms 			= new HashMap<Integer, Room>();
	public HashMap<Long, PlayerState>  players_searching = new HashMap<Long, PlayerState>();
	public HashMap<Long, PlayerState>  players_in_battle = new HashMap<Long, PlayerState>();
	
	public static RoomMgr instance() {
		if(inst==null)
			inst = new RoomMgr();

		return inst;
	}
	
	public static void update(){
		RoomMgr.instance().process(1.f);
	}
	
	public void process(float delta) {
		// ƥ��
		if(players_searching.size()>2) {
			Iterator it = players_searching.entrySet().iterator();
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Long player0 = (Long) pair.getKey();
			it.remove();
			
			pair = (HashMap.Entry)it.next();
			Long player1 = (Long) pair.getKey();
			it.remove();
			
			new_room(player0, player1);
		}
		
		// ���·���
		Iterator it = rooms.entrySet().iterator();
		while(it.hasNext()){
			HashMap.Entry pair = (HashMap.Entry)it.next();
			((Room) pair.getValue()).process(delta);
		}
	}
	
	public void new_room(long player0, long player1) {
		Room room = new Room();
		
		PlayerState ps0 = new PlayerState(player0, room.hashCode());
		players_in_battle.put(player0, ps0);
		
		PlayerState ps1 = new PlayerState(player0, room.hashCode());
		players_in_battle.put(player1, ps1);
		
		rooms.put(room.hashCode(), room);
	}
	
	public boolean add_player(long player) {
		if(players_searching.containsKey(player)) {
			return false;
		}
		else {
			players_searching.put(player, new PlayerState(player, 0));
			return true;
		}
	}
	
	public boolean remove_player(long player){
		if(players_searching.containsKey(player)) {
			players_searching.remove(player);
			return true;
		}
		else {
			return false;
		}
	}
}