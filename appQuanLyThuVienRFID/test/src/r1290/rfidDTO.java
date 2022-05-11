package r1290;

public class rfidDTO {
	String EPC;
	String Antenna;
	String TID;
	int RSSI;
	public rfidDTO(String EPC, String Antenna, String TID, int RSSI) {
		this.EPC = EPC;
		this.Antenna = Antenna;
		this.TID = TID;
		this.RSSI = RSSI;
	}
	public rfidDTO()
    {
        
    }
}
