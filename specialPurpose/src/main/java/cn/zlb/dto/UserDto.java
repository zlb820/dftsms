package cn.zlb.dto;

public class UserDto {
public String name;
public String pass;


public UserDto() {
	super();
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public String getPass() {
	return pass;
}
@Override
public String toString() {
	return "UserDto [name=" + name + ", pass=" + pass + "]";
}
public UserDto(String name, String pass) {
	super();
	this.name = name;
	this.pass = pass;
}


}
