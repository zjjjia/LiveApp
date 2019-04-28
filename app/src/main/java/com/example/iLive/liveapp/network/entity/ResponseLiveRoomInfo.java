package com.example.iLive.liveapp.network.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/9
 * Description : 拉取的直播房间列表
 * Modify by
 */

public class ResponseLiveRoomInfo {
    private Integer total;
    private List<Room> rooms = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "ResponseLiveRoomInfo{" +
                "total=" + total +
                ", rooms=" + rooms +
                '}';
    }

    /**
     * 房间信息
     */
    public class Room {
        private String uid;
        private Info info;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }
    }

    /**
     * 房间具体信息
     */
    public class Info {
        private String title;
        /**
         * 房间类型
         */
        private String type;
        private Integer roomnum;
        private String groupid;
        private String cover;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getRoomnum() {
            return roomnum;
        }

        public void setRoomnum(Integer roomnum) {
            this.roomnum = roomnum;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", roomnum=" + roomnum +
                    ", groupid='" + groupid + '\'' +
                    ", cover='" + cover + '\'' +
                    '}';
        }
    }
}
