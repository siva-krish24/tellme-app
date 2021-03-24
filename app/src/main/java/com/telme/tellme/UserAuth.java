package com.telme.tellme;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId"
})
public class UserAuth {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString(){
        return "{" + "\"userId\" : " + "\"" + userId+ "\""+ "}";

    }
}