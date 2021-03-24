package andy.inc.cargamev1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
        Handler handler = new Handler();
        CarView carView;
        Display display;
        static Point size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        display = getWindowManager().getDefaultDisplay();

        size = new Point();
        display.getSize(size); int width = size.x; int height = size.y;
        carView = new CarView(this);
        setContentView(carView);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        carView.invalidate();
                    }
                });
            }
        }, 0, 30);
    }
}