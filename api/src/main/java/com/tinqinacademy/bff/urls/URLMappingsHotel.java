package com.tinqinacademy.bff.urls;

public class URLMappingsHotel {

    public static final String GET_ROOM = "/hotel/room/";
    public static final String GET_ROOM_BY_ID= "/hotel/{roomId}";
    public static final String POST_BOOK_ROOM_BY_ID= "/hotel/{roomId}";
    public static final String DELETE_BOOKING_BY_ID= "/hotel/{bookingId}";

    public static final String POST_REGISTER_VISITOR= "/system/register";
    public static final String GET_REPORT ="/system/register";
    public static final String POST_CREATE_ROOM = "/system/room";
    public static final String PATCH_EDIT_ROOM = "/system/room/{roomId}";
    public static final String PUT_UPDATE_ROOM = "/system/room/{roomId}";
    public static final String DELETE_ROOM = "/system/room/{roomId}";
}
