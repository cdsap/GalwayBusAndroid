package com.surrus.galwaybus;


import android.app.Application;

import dagger.ObjectGraph;

public class GalwayBusApplication extends Application {

    private ObjectGraph objectGraph = createGraph();

    @Override
    public void onCreate() {
        super.onCreate();

  //      objectGraph = ObjectGraph.create(new AppModule());
  //      objectGraph.inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    protected ObjectGraph createGraph() {
        objectGraph = ObjectGraph.create(new AppModule());
        objectGraph.inject(this);
        return objectGraph;
    }

}
