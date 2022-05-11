package r1290;

public class UserDTO {
	int user_id;
	String user_name;
	String user_phone;
	String username;
	String password;
	public UserDTO(int user_id, String user_name, String user_phone, String username, String password) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.username = username;
		this.password = password;
	}
	public UserDTO()
    {
        
    }
}
