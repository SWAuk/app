package uk.co.swa.swapp.model;

/**
 * Created by oliver on 20/03/2016.
 */
public abstract class SwaObject {
    protected long appID;

    public SwaObject(long appID) {
        this.appID = appID;
    }

    public long getAppID() {
        return appID;
    }

    public void setAppID(long appID) {
        this.appID = appID;
    }
}
