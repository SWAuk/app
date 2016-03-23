package uk.co.swa.swapp;

import uk.co.swa.swapp.store.MockDataStore;
import uk.co.swa.swapp.store.Store;

/**
 * This is the app's God Class.
 */
public class God {
    private static God ourInstance = new God();

    private Store store;

    public static God getInstance() {
        return ourInstance;
    }

    private God() {
        this.store = new MockDataStore();
    }

    public Store getStore() {
        return this.store;
    }
}
