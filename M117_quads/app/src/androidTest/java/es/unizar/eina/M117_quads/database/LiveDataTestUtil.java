package es.unizar.eina.M117_quads.database;

import androidx.lifecycle.LiveData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {

    public static <T> T getValue(LiveData<T> liveData) {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);

        liveData.observeForever(o -> {
            data[0] = o;
            latch.countDown();
        });

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //noinspection unchecked
        return (T) data[0];
    }
}
