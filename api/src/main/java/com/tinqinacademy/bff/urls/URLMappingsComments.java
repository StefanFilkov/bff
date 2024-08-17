package com.tinqinacademy.bff.urls;

public class URLMappingsComments {

    public static final String GET_COMMENTS_BY_ROOM_ID ="/hotel/{roomId}/comment";
    public static final String POST_COMMENT = "/hotel/{roomId}/comment";
    public static final String PATCH_COMMENT = "/hotel/comment/{commentId}";
    public static final String PUT_COMMENT = "/system/comment/{commentId}";
    public static final String DELETE_COMMENT = "/system/comment/{commentId}";
}
