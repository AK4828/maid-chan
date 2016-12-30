package com.butuhpembantu.application;

import android.app.Application;

/**
 * Created by akm on 12/30/16.
 */

public class ButuhPembantuApplication extends Application {

    private static ButuhPembantuApplication instance;

    public ButuhPembantuApplication() {
        instance = this;
    }

    public static ButuhPembantuApplication getInstance() {
        return instance;
    }
}
