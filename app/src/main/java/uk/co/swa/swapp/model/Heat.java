package uk.co.swa.swapp.model;

public class Heat implements SwaObject {

    long appID;

    public Heat(long appID) {
        this.appID = appID;
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }
}
