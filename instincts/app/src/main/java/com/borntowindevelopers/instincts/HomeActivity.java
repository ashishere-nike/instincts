
package com.borntowindevelopers.instincts;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.borntowindevelopers.instincts.ui.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class HomeActivity extends Activity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private HomeActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemContact;
    private ResideMenuItem itemAbout;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "HomeActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
 //   private ProgressBar mRegistrationProgressBar;
 //   private TextView mInformationTextView;

    // Bitmaps will only be decoded once and stored in this cache
    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(11);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main2);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions =
              View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
           //     mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
           //         mInformationTextView.setText(getString(R.string.gcm_send_message));
                } else {
           //         mInformationTextView.setText(getString(R.string.token_error_message));
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        mContext = this;
        setUpMenu();

        // Used to get the dimensions of the image views to load scaled down bitmaps
        final View parent = findViewById(R.id.parent);
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListenerCompat(parent, this);
                setImageBitmap((ImageView) findViewById(R.id.cardchild1_1).findViewById(R.id.photo), R.drawable.proshow);
                setImageBitmap((ImageView) findViewById(R.id.cardchild1_2).findViewById(R.id.photo), R.drawable.elan);
                setImageBitmap((ImageView) findViewById(R.id.cardchild2_1).findViewById(R.id.photo), R.drawable.choreo5);
                setImageBitmap((ImageView) findViewById(R.id.cardchild2_2).findViewById(R.id.photo), R.drawable.dj);
                setImageBitmap((ImageView) findViewById(R.id.cardchild3_1).findViewById(R.id.photo), R.drawable.rof);
                setImageBitmap((ImageView) findViewById(R.id.cardchild3_2).findViewById(R.id.photo), R.drawable.saaral);
                setImageBitmap((ImageView) findViewById(R.id.cardchild4_1).findViewById(R.id.photo), R.drawable.game);
                setImageBitmap((ImageView) findViewById(R.id.cardchild4_2).findViewById(R.id.photo), R.drawable.informals);
                setImageBitmap((ImageView) findViewById(R.id.cardchild5_1).findViewById(R.id.photo), R.drawable.map);
                setImageBitmap((ImageView) findViewById(R.id.cardchild5_2).findViewById(R.id.photo), R.drawable.bus);

            }
        });

    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
        int uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));

     //   if (!Utils.hasLollipop()) {
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

     //   }
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
        sPhotoCache.put(resId, bitmap);
        imageView.setImageBitmap(bitmap);
    }
    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.folderback722);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "Register");
        itemCalendar = new ResideMenuItem(this, R.drawable.ic_star, "Events");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_calendar, "Schedule");
        itemContact = new ResideMenuItem(this, R.drawable.ic_info, "Contact");
        itemAbout = new ResideMenuItem(this, R.drawable.icon_profile, "About Us");
     //   itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Schedule");

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemContact.setOnClickListener(this);
        itemAbout.setOnClickListener(this);


        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemContact, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAbout, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

       findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        // findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        //       resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
        //}
        //});
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
@Override
    public void onClick(View view) {
        Intent in;
        if (view == itemHome) {

        } else if (view == itemProfile) {
      //      in = new Intent(HomeActivity.this,RegisterActivity.class);
      //      startActivity(in);
        } else if (view == itemCalendar) {
            in = new Intent(HomeActivity.this,EventActivity.class);
            startActivity(in);
        } else if (view == itemSettings) {

        } else if (view == itemContact) {

        } else if (view == itemAbout) {

        }

    resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    public ResideMenu getResideMenu() {
        return resideMenu;
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
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 37.6329946)
                        .putExtra("lng", -122.4938344)
                        .putExtra("zoom", 2.0f)
                        .putExtra("title", "Pro Show")
                        .putExtra("description", "Quality defines a musical night. Add to it some Energy, Variety and an Electric Atmosphere. The signature show of SSN's Instincts is the ever-lively Pro Show which takes place on the first night if Instincts. We have a proud history attractingg the likes of Instrumentalists Shivamani, Stephen Devasy, vocalists Karthik, Haricharan, Rita, Suchitra, Rapper Blazze, Ranjith, Sakthisree Gopalan, Sunitha Sarathy and many others for this musical treat.")
                        .putExtra("photo", R.drawable.proshow);
                resId = R.id.cardchild1_1;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild1_2:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 37.6329946)
                        .putExtra("lng", -122.4938344)
                        .putExtra("zoom", 14.0f)
                        .putExtra("title", "Elan")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.elan);
                resId = R.id.cardchild1_2;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild2_1:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 37.73284)
                        .putExtra("lng", -122.503065)
                        .putExtra("zoom", 15.0f)
                        .putExtra("title", "Choreo Night")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.choreo5);
                resId = R.id.cardchild2_1;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild2_2:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 36.861897)
                        .putExtra("lng", -111.374438)
                        .putExtra("zoom", 11.0f)
                        .putExtra("title", "Dj Night")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.dj);
                resId = R.id.cardchild2_2;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild3_1:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Reels of Fire")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.rof);
                resId = R.id.cardchild3_1;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild3_2:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Saaral")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.saaral);
                resId = R.id.cardchild3_2;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild4_1:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Gaming")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.game);
                resId = R.id.cardchild4_1;
                startActivityGingerBread(view, intent, resId);

                break;
            case R.id.showchild4_2:
                intent.setClass(this, DetailActivity.class);
                intent.putExtra("lat", 12.750859)
                        .putExtra("lng", 80.197257)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Informals")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.informals);
                resId = R.id.cardchild4_2;
                    startActivityGingerBread(view, intent, resId);
                break;
            case R.id.showchild5_1:
//                intent.setClass(this, MapActivity.class);
                    Intent in = new Intent(HomeActivity.this,MapActivity.class);
                    startActivity(in);

                break;
            case R.id.showchild5_2:

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
