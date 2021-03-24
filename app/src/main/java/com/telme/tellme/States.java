package com.telme.tellme;

public class States {
    private  int total;
    private int followups;
    private int notanswered;
    private int notintrested;
    private int logins;
    private int closed;

    public States() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFollowups() {
        return followups;
    }

    public void setFollowups(int followups) {
        this.followups = followups;
    }

    public int getNotanswered() {
        return notanswered;
    }

    public void setNotanswered(int notanswered) {
        this.notanswered = notanswered;
    }

    public int getNotintrested() {
        return notintrested;
    }

    public void setNotintrested(int notintrested) {
        this.notintrested = notintrested;
    }

    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins = logins;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }
}
