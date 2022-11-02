package com.oyster.kong.domain;

import java.util.Date;
import java.util.Objects;

public class MemberDto {
	
	private String id;
	private String pwd;
	private String name;
	private Date birth;
	private String post;
	private String addr1;
	private String addr2;
	private String addr3;
	private String sns;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email1;
	private String email2;
	private Date reg_date;
	
	public MemberDto() {}

	public MemberDto(String id, String pwd, String name, Date birth, String post, String addr1, String addr2,
			String addr3, String sns, String phone1, String phone2, String phone3, String email1, String email2,
			Date reg_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.sns = sns;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.email1 = email1;
		this.email2 = email2;
		this.reg_date = reg_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", birth=" + birth + ", post=" + post
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3=" + addr3 + ", sns=" + sns + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", phone3=" + phone3 + ", email1=" + email1 + ", email2=" + email2
				+ ", reg_date=" + reg_date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addr1, addr2, addr3, birth, email1, email2, id, name, phone1, phone2, phone3, post, pwd,
				reg_date, sns);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDto other = (MemberDto) obj;
		return Objects.equals(addr1, other.addr1) && Objects.equals(addr2, other.addr2)
				&& Objects.equals(addr3, other.addr3) && Objects.equals(birth, other.birth)
				&& Objects.equals(email1, other.email1) && Objects.equals(email2, other.email2)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phone1, other.phone1) && Objects.equals(phone2, other.phone2)
				&& Objects.equals(phone3, other.phone3) && Objects.equals(post, other.post)
				&& Objects.equals(pwd, other.pwd) && Objects.equals(reg_date, other.reg_date)
				&& Objects.equals(sns, other.sns);
	}

	
	


}
