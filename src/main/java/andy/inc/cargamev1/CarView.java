package andy.inc.cargamev1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class CarView extends View {
    private Random random = new Random();
    private Bitmap backdrop;
    private Bitmap ucar;
    private Bitmap aicar1;
    private Bitmap aicar2;
    private Bitmap aicar3;
    private Bitmap gameover;
    private Bitmap restart;
    private Paint paint = new Paint();
    private boolean touch;
    private float X = 50, Y = 3 * MainActivity.size.y / 4;
    public int aiy1 = random.nextInt(4) * 50 + 16;
    public int aiy2 = random.nextInt(4) * 50 + 16;
    public int aiy3 = random.nextInt(4) * 50 + 16;
    public int aix1 = random.nextInt(2)  * 100 + 50;
    public int aix2 = 2 - (((aix1 - 50)/100) + 1) * 100 + 100;
    public int aix3 = (random.nextInt(2) + 2) * 100 + 50;
    public boolean[] widthOccupied = new boolean[3];
    private boolean isgameOver = false;
    public CarView(Context context) {
        super(context);
        backdrop = BitmapFactory.decodeResource(getResources(), R.drawable.backdrop);
        gameover = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
        ucar = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_1);
        aicar1 = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_2);
        aicar2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_3);
        aicar3 = BitmapFactory.decodeResource(getResources(), R.drawable.car_right_1);
        restart = BitmapFactory.decodeResource(getResources(), R.drawable.restart);
        backdrop = Bitmap.createScaledBitmap(backdrop, MainActivity.size.x, MainActivity.size.y, false);
        gameover = Bitmap.createScaledBitmap(gameover, MainActivity.size.x, MainActivity.size.y, false);
        aicar1 = Bitmap.createScaledBitmap(aicar1, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        aicar2 = Bitmap.createScaledBitmap(aicar2, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        aicar3 = Bitmap.createScaledBitmap(aicar3, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        ucar = Bitmap.createScaledBitmap(ucar, MainActivity.size.x / 6, MainActivity.size.y / 6, false);
        //TODO: Maybe an issue might come with the filter boolean

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
if(isgameOver) {

    }
else {
    canvas.drawBitmap(backdrop, 0, 0, paint);
    canvas.drawBitmap(ucar, X, Y, paint);
    //TODO: add the aicar on screen and movement + collision
    canvas.drawBitmap(aicar1, aix1, aiy1, paint);
    canvas.drawBitmap(aicar2, aix2 + 100, aiy2, paint);
    canvas.drawBitmap(aicar3, aix3, aiy3, paint);
    aiy1 += random.nextInt(25);
    aiy2 += random.nextInt(25);
    aiy3 += random.nextInt(25);

    if (aiy1 > MainActivity.size.y) {
        aiy1 = random.nextInt(4) * 50 + 16;
        aix1 = random.nextInt(2) * 100 + 150;
    } else if (aiy2 >= MainActivity.size.y) {
        aiy2 = random.nextInt(4) * 50 + 16;
        aix2 = 2 - (((aix1 - 50) / 100) + 1) * 100 + 200;
    } else if (aiy3 > MainActivity.size.y) {
        aiy3 = random.nextInt(4) * 50 + 16;
        aix3 = (random.nextInt(2) + 2) * 100 + 250;
    }
}
        if (ifTouching(Math.round(X), aix1, Math.round(Y), aiy1)) {
            //TODO: Add game over logic
            canvas.drawBitmap(gameover, 0, 0, paint);
            canvas.drawBitmap(restart, 2 * MainActivity.size.x/4, 2 * MainActivity.size.y/3, paint);
            isgameOver = true;
        }
        if (ifTouching(Math.round(X), aix2, Math.round(Y), aiy2)) {
            //TODO: Add game over logic
            canvas.drawBitmap(gameover, 0, 0, paint);
            canvas.drawBitmap(restart, 2 * MainActivity.size.x/4, 2 * MainActivity.size.y/3, paint);
            isgameOver = true;
        }
        if (ifTouching(Math.round(X), aix3, Math.round(Y), aiy3)) {
            //TODO: Add game over logic
            canvas.drawBitmap(gameover, 0, 0, paint);
            canvas.drawBitmap(restart, 2 * MainActivity.size.x/4, 2 * MainActivity.size.y/3, paint);
            isgameOver = true;
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && isgameOver != true) {
            X = event.getX();
            touch = true;
        }
        return true;
    }
    private boolean ifTouching(int x1, int x2, int y1, int y2){
        if(x1 <= x2 + 20  && x1 >= x2 - 20 ){
            if (y1 <= y2 + 20 && y1 >= y2 - 20){
                return true;
            }
        }
        return false;
    }

}