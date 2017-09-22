package org.code.server.model;

public class Channel {
    private Integer cid;

    private String name;

    private String status;

    private String starttime;

    private String stoptime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime == null ? null : stoptime.trim();
    }
}