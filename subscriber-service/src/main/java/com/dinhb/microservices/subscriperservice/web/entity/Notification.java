package com.dinhb.microservices.subscriperservice.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification implements Serializable {
	private String username;
	private String title;
	private String description;
}
