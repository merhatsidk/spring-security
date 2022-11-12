package edu.miu.cs545.restApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String userName;
    private String password;
    private Boolean isAvailable;
//    private List<Post> posts;

}
