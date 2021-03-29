package andy.inc.cargamev1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class CarView extends View{
    private final Random random = new Random();
    private Bitmap backdrop;
    private Bitmap ucar;
    private Bitmap aicar1;
    private Bitmap aicar2;
    private Bitmap aicar3;
    private final Paint paint = new Paint();
    private boolean touch;
    private Bitmap gameOver;
    private Bitmap restart;
    private Rect imageRect = new Rect(MainActivity.size.x/4, 3 * MainActivity.size.y/4, MainActivity.size.x/2 + MainActivity.size.x/2, MainActivity.size.y/8 - MainActivity.size.y/8);
    /*{
        b.setVisibility(INVISIBLE);
    }*/
    private float X = 50;
    private final float Y = 3 * MainActivity.size.y / 4;
    public int aiy1 = random.nextInt(4) * 50 + 16;
    public int aiy2 = random.nextInt(4) * 50 + 16;
    public int aiy3 = random.nextInt(4) * 50 + 16;
    public int aix1 = random.nextInt(2)  * 100 + 50;
    public int aix2 = 2 - (((aix1 - 50)/100) + 1) * 100 + 100;
    public int aix3 = (random.nextInt(2) + 2) * 100 + 50;
    public boolean[] widthOccupied = new boolean[3];
    private boolean isgameOver = false;
    private boolean restartPressed = false;
    public CarView(Context context) {
        super(context);
        restart = BitmapFactory.decodeResource(getResources(), R.drawable.restart);
        gameOver = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
        backdrop = BitmapFactory.decodeResource(getResources(), R.drawable.backdrop);
        ucar = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_1);
        aicar1 = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_2);
        aicar2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_left_3);
        aicar3 = BitmapFactory.decodeResource(getResources(), R.drawable.car_right_1);
        restart = Bitmap.createScaledBitmap(restart, MainActivity.size.x/2, MainActivity.size.y/8, false);
        gameOver = Bitmap.createScaledBitmap(gameOver, MainActivity.size.x, MainActivity.size.y, false);
        backdrop = Bitmap.createScaledBitmap(backdrop, MainActivity.size.x, MainActivity.size.y, false);
        aicar1 = Bitmap.createScaledBitmap(aicar1, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        aicar2 = Bitmap.createScaledBitmap(aicar2, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        aicar3 = Bitmap.createScaledBitmap(aicar3, (MainActivity.size.x / 6) - 8, (MainActivity.size.y / 6) - 8, false);
        ucar = Bitmap.createScaledBitmap(ucar, MainActivity.size.x / 6, MainActivity.size.y / 6, false);

        //TODO: Maybe an issue might come with the filter boolean

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(restartPressed){
            restartPressed = false;
            isgameOver = false;
            aiy1 = random.nextInt(4) * 50 + 16;
            aiy2 = random.nextInt(4) * 50 + 16;
            aiy3 = random.nextInt(4) * 50 + 16;
            aix1 = random.nextInt(2)  * 100 + 50;
            aix2 = 2 - (((aix1 - 50)/100) + 1) * 100 + 100;
            aix3 = (random.nextInt(2) + 2) * 100 + 50;
        }
if(isgameOver) {
    canvas.drawBitmap(gameOver, 0, 0, paint);
    canvas.drawBitmap(restart, MainActivity.size.x/4, 3 * MainActivity.size.y/4, paint);
    }
else {

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
    canvas.drawBitmap(backdrop, 0, 0, paint);
    canvas.drawBitmap(ucar, X, Y, paint);
    //TODO: add the aicar on screen and movement + collision
    canvas.drawBitmap(aicar1, aix1, aiy1, paint);
    canvas.drawBitmap(aicar2, aix2 + 100, aiy2, paint);
    canvas.drawBitmap(aicar3, aix3, aiy3, paint);
    if (ifTouching(Math.round(X), aix1, Math.round(Y), aiy1)) {
        //TODO: Add game over logic
        canvas.drawBitmap(gameOver, 0, 0, paint);/*
        MainActivity.gameOverActivity();*/
        canvas.drawBitmap(restart, MainActivity.size.x/4, 3 * MainActivity.size.y/4, paint);

                isgameOver = true;
    }
    if (ifTouching(Math.round(X), aix2, Math.round(Y), aiy2)) {
        //TODO: Add game over logic
        canvas.drawBitmap(gameOver, 0, 0, paint);/*
        MainActivity.gameOverActivity();*/
        canvas.drawBitmap(restart, MainActivity.size.x/4, 3 * MainActivity.size.y/4, paint);
        isgameOver = true;
    }
    if (ifTouching(Math.round(X), aix3, Math.round(Y), aiy3)) {
        //TODO: Add game over logic
        canvas.drawBitmap(gameOver, 0, 0, paint);/*
        MainActivity.gameOverActivity();*/
         canvas.drawBitmap(restart, MainActivity.size.x/4, 3 * MainActivity.size.y/4, paint);
        isgameOver = true;
    }
    aiy1 += random.nextInt(25);
    aiy2 += random.nextInt(25);
    aiy3 += random.nextInt(25);


}

    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !isgameOver) {
            X = event.getX();
            touch = true;
        }
        else if(isgameOver){
            restartPressed = true;

        }
        return true;

    }
    private boolean ifTouching(int cx, int ax, int cy, int ay){
        if(cx <= ax + 170  && cx >= ax - 100) {
            if (cy <= ay + 50 && cy >= ay - 150) {
                return true;
            }
        }
        return false;
    }

}
