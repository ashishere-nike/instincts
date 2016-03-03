package com.borntowindevelopers.instincts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.borntowindevelopers.instincts.ui.Utils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class EventActivity extends Activity {

    Button back;
    // Bitmaps will only be decoded once and stored in this cache
    public static SparseArray<Bitmap> sPhotoCache1 = new SparseArray<Bitmap>(25);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
      //  getSupportActionBar().setHomeButtonEnabled(true);

        back = (Button)findViewById(R.id.back);

        View decorView = getWindow().getDecorView();
        int uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);


        // Used to get the dimensions of the image views to load scaled down bitmaps
        final View parent = findViewById(R.id.parent);
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListenerCompat(parent, this);
                setImageBitmap((ImageView) findViewById(R.id.cardchild1_1).findViewById(R.id.photo), R.drawable.dance);
                setImageBitmap((ImageView) findViewById(R.id.cardchild1_2).findViewById(R.id.photo), R.drawable.music);
                setImageBitmap((ImageView) findViewById(R.id.cardchild2_1).findViewById(R.id.photo), R.drawable.finearts);
                setImageBitmap((ImageView) findViewById(R.id.cardchild2_2).findViewById(R.id.photo), R.drawable.saaral);
                setImageBitmap((ImageView) findViewById(R.id.cardchild3_1).findViewById(R.id.photo), R.drawable.quiz);
                setImageBitmap((ImageView) findViewById(R.id.cardchild3_2).findViewById(R.id.photo), R.drawable.englishclub);
                setImageBitmap((ImageView) findViewById(R.id.cardchild4_1).findViewById(R.id.photo), R.drawable.game);
                setImageBitmap((ImageView) findViewById(R.id.cardchild4_2).findViewById(R.id.photo), R.drawable.informals);
                setImageBitmap((ImageView) findViewById(R.id.cardchild5_1).findViewById(R.id.photo), R.drawable.photography);
                setImageBitmap((ImageView) findViewById(R.id.cardchild5_2).findViewById(R.id.photo), R.drawable.lop);

            }
        });

    }

    public void goBack(View v){

        Intent intent = new Intent(EventActivity.this,HomeActivity.class);
        EventActivity.this.finish();
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
        int uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);

        // The activity transition animates the clicked image alpha to zero, reset that value when
            // you come back to this activity
            ViewHelper.setAlpha(findViewById(R.id.cardchild1_1), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild1_2), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild2_1), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild2_2), 1.0f);


            ViewHelper.setAlpha(findViewById(R.id.cardchild3_1), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild3_2), 1.0f);
            ViewHelper.setAlpha(findViewById(R.id.cardchild4_1), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild4_2), 1.0f);
            ViewHelper.setAlpha(findViewById(R.id.cardchild5_1), 1.0f);

            ViewHelper.setAlpha(findViewById(R.id.cardchild5_2), 1.0f);

       }

    /**
     * Loads drawables into the given image view efficiently. Uses the method described
     * <a href="http://developer.android.com/training/displaying-bitmaps/load-bitmap.html">here.</a>
     *
     * @param imageView
     * @param resId     Resource identifier of the drawable to load from memory
     */
    private void setImageBitmap(ImageView imageView, int resId) {
        Bitmap bitmap = Utils.decodeSampledBitmapFromResource(getResources(),
                resId, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        sPhotoCache1.put(resId, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * When the user clicks a thumbnail, bundle up information about it and launch the
     * details activity.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void showPhoto(View view) {
        Intent intent = new Intent();


        // Interesting data to pass across are the thumbnail location, the map parameters,
        // the picture title & description, and the key to retrieve the bitmap from the cache
        int resId = 0;
        switch (view.getId()) {
            case R.id.showchild1_1:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 37.6329946)
                        .putExtra("lng", -122.4938344)
                        .putExtra("zoom", 2.0f)
                        .putExtra("title", "Dance")
                        .putExtra("description", "Quality defines a musical night. Add to it some Energy, Variety and an Electric Atmosphere. The signature show of SSN's Instincts is the ever-lively Pro Show which takes place on the first night if Instincts. We have a proud history attractingg the likes of Instrumentalists Shivamani, Stephen Devasy, vocalists Karthik, Haricharan, Rita, Suchitra, Rapper Blazze, Ranjith, Sakthisree Gopalan, Sunitha Sarathy and many others for this musical treat.")
                        .putExtra("photo1", R.drawable.dance);
                resId = R.id.cardchild1_1;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild1_2:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 37.6329946)
                        .putExtra("lng", -122.4938344)
                        .putExtra("zoom", 14.0f)
                        .putExtra("title", "Music")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.music);
                resId = R.id.cardchild1_2;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild2_1:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 37.73284)
                        .putExtra("lng", -122.503065)
                        .putExtra("zoom", 15.0f)
                        .putExtra("title", "Fine Arts")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.finearts);
                resId = R.id.cardchild2_1;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild2_2:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 36.861897)
                        .putExtra("lng", -111.374438)
                        .putExtra("zoom", 11.0f)
                        .putExtra("title", "Saaral Tamil Mandram")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.saaral);
                resId = R.id.cardchild2_2;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild3_1:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Quiz")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.quiz);
                resId = R.id.cardchild3_1;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild3_2:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "English Literary Events")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.englishclub);
                resId = R.id.cardchild3_2;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild4_1:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Gaming")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.game);
                resId = R.id.cardchild4_1;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild4_2:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 12.750859)
                        .putExtra("lng", 80.197257)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Informals")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.informals);
                resId = R.id.cardchild4_2;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild5_1:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 12.750859)
                        .putExtra("lng", 80.197257)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Photography")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.photography);
                resId = R.id.cardchild5_1;
                    startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild5_2:
                intent.setClass(this, EDetailActivity.class);
                intent.putExtra("lat", 12.750859)
                        .putExtra("lng", 80.197257)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Lights Out Please")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo1", R.drawable.lop);
                resId = R.id.cardchild5_2;
                    startActivityGingerBread(view, intent, resId);

                break;

        }


    }


    private void startActivityGingerBread(View view, Intent intent, int resId) {
        int[] screenLocation = new int[2];
        view.getLocationOnScreen(screenLocation);
        intent.
                putExtra("left", screenLocation[0]).
                putExtra("top", screenLocation[1]).
                putExtra("width", view.getWidth()).
                putExtra("height", view.getHeight());

        startActivity(intent);

        // Override transitions: we don't want the normal window animation in addition to our
        // custom one
        overridePendingTransition(0, 0);

        // The detail activity handles the enter and exit animations. Both animations involve a
        // ghost view animating into its final or initial position respectively. Since the detail
        // activity starts translucent, the clicked view needs to be invisible in order for the
        // animation to look correct.
        ViewPropertyAnimator.animate(findViewById(resId)).alpha(0.0f);
    }
}
