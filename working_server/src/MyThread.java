import java.io.IOException;

import jxl.read.biff.BiffException;

public class MyThread extends Thread {
    private String path;
    MyUpdateListenr listener;
    String mounth;

    MyThread(String path, String mounth) {
        this.path = path;
        this.mounth = mounth;
    }

    public void setOnListener(MyUpdateListenr listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            String result = HandlerManager.handlerSheet(path, mounth);
            listener.process(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    interface MyUpdateListenr {
        public void process(String res);
    }
}
