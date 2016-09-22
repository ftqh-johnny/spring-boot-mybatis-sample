package com.ft.turorial.spring.boot.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable{
	
	private static final long serialVersionUID = -8971505764090290428L;
	private Integer id;
    private String name;
	private Date birthday;
	private String userName;
	private String password;
	
	private String salt;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCredentialsSalt(){
		return salt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + ", userName=" + userName + ", password="
				+ password + ", salt=" + salt + "]";
	}
}
