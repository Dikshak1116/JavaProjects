package com.blog.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String about;
}
