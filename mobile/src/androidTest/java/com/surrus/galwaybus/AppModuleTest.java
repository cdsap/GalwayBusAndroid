package com.surrus.galwaybus;


import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.surrus.galwaybus.service.GalwayBusService;
import com.surrus.galwaybus.ui.RoutesFragment;
import com.surrus.galwaybus.ui.StopsActivity;
import com.surrus.galwaybus.ui.StopsMapActivity;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusBuilder;
import retrofit.RestAdapter;

@Module(
        library = true,
        injects = {
                GalwayBusApplication.class,
                RoutesFragment.class,
                GalwayBusService.class,
                StopsActivity.class,
                StopsActivity.StopsFragment.class,
                StopsMapActivity.class
        }
)
public class AppModuleTest {

    @Provides
    @Singleton
    public EventBus providesBus() {
        EventBusBuilder builder = EventBus.builder();
        return builder.build();
    }

    @Provides
    @Singleton
    public GalwayBusService providesInterface() {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            mockWebServer.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .build();
        return restAdapter.create(GalwayBusService.class);
    }

}