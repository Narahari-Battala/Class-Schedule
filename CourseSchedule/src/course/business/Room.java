package course.business;

public class Room {

	private int roomId;
	private int roomNum;
	private int capacity;
	private int building;
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getBuilding() {
		return building;
	}
	public void setBuilding(int building) {
		this.building = building;
	}
	public Room(int roomId, int roomNum, int capacity, int building) {
		super();
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.capacity = capacity;
		this.building = building;
	}
	
	
}
