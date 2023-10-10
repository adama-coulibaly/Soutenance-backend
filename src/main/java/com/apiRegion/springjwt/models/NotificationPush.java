package com.apiRegion.springjwt.models;

import java.util.Map;
import lombok.Data;
@Data
public class NotificationPush {
private String token;
private String title;
private String body;
private String image;
private Map<String, String> data;
}