package com.surrus.galwaybus;

import dagger.ObjectGraph;

/**
 * Created by inaki on 26/01/16.
 */
public class MockApplication extends GalwayBusApplication {


    @Override
    protected ObjectGraph createGraph() {
        ObjectGraph objectGraph = ObjectGraph.create(new AppModuleTest());
        objectGraph.inject(this);
        return objectGraph;

    }
}
