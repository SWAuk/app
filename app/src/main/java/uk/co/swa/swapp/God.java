package uk.co.swa.swapp;

import uk.co.swa.swapp.store.MainStore;
import uk.co.swa.swapp.store.MockAppStore;
import uk.co.swa.swapp.store.AppStore;
import uk.co.swa.swapp.store.MockMainStore;

/**
 * This is the app's God Class.
 */
public class God {

    private static God ourInstance = new God();

    private AppStore appStore;
    private MainStore mainStore;

    public static God getInstance() {
        return ourInstance;
    }

    private God() {
        this.appStore = new MockAppStore();
        this.mainStore = new MockMainStore();
    }

    public AppStore getAppStore() {
        return this.appStore;
    }

    public MainStore getMainStore() {
        return this.mainStore;
    }

}
