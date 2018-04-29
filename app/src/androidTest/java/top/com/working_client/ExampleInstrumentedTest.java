package top.com.working_client;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import top.com.working_client.database.WorkingSheet;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context context = InstrumentationRegistry.getContext();
        WorkingSheetDao_old dao = new WorkingSheetDao_old(context);
        List<WorkingSheet> workingSheets = dao.queryEvery();
    }
}
