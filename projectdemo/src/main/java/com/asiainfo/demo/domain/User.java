package com.asiainfo.demo.domain;

public class User {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.id
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.username
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.address
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.email
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.ssoid
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private String ssoid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.password
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	private String password;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.id
	 * @return  the value of user.id
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.id
	 * @param id  the value for user.id
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.username
	 * @return  the value of user.username
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.username
	 * @param username  the value for user.username
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.address
	 * @return  the value of user.address
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.address
	 * @param address  the value for user.address
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.email
	 * @return  the value of user.email
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.email
	 * @param email  the value for user.email
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.ssoid
	 * @return  the value of user.ssoid
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public String getSsoid() {
		return ssoid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.ssoid
	 * @param ssoid  the value for user.ssoid
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.password
	 * @return  the value of user.password
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.password
	 * @param password  the value for user.password
	 * @mbg.generated  Sat Feb 11 21:56:05 CST 2017
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address + ", email=" + email + ", ssoid="
				+ ssoid + ", password=" + password + "]";
	}
	
	
}