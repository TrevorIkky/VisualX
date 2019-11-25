package com.io.org.visualx.libs.service;


import android.content.Intent;

import com.io.org.visualx.libs.HttpRequestStore;
import com.io.org.visualx.libs.domain.RequestOptions;
import com.io.org.visualx.libs.domain.RequestWrapper;


public class HTTPRequestExecutorService extends BaseObservableThreadPoolServiceService {

    @Override
    public void handleIntent(final Intent intent) {
        RequestWrapper wrapper = HttpRequestStore.getInstance(getApplicationContext()).getRequest(intent);
        RequestOptions options = wrapper.getOptions();
        if (options.shouldRunInSingleThread() == false) {
            getFixedSizePoolExecutor().execute(new WorkerThread(options.getPriority(), wrapper.getRequest()));
            return;
        }

        // Handle according to options

        getSingleThreadExecutorService().execute(new WorkerThread(options.getPriority(), wrapper.getRequest()));

    }

}
