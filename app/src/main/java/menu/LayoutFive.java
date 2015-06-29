package menu;

/**
 * Created by AdminPond on 21/6/2558.
 */

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cm_smarthome.materialdesignsupportlibrary.MainActivity;
import com.cm_smarthome.materialdesignsupportlibrary.R;
import com.dd.CircularProgressButton;

import login.Login_activity;

public class LayoutFive extends Fragment {
    Sqlite sqlite;
    private BootstrapButton btnStart;

    public static LayoutFive newInstance() {
        LayoutFive fragment = new LayoutFive();
        return fragment;
    }

    public LayoutFive() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_five, null);

        sqlite = new Sqlite(getActivity());

       /* btnStart = (BootstrapButton) root.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlite.UpdateDataFlag("1", "1");
                String[] arrData = sqlite.SelectData("1");
                Log.e("Flag", arrData[1]);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "OK...", Toast.LENGTH_SHORT).show();
            }
        });*/

        final CircularProgressButton btnWithIcons2 = (CircularProgressButton) root.findViewById(R.id.circularButton1);
        btnWithIcons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnWithIcons2.getProgress() == 0) {
                    simulateSuccessProgress(btnWithIcons2);
                    sqlite.UpdateDataFlag("1", "1");
                } else {
                    String[] arrData = sqlite.SelectData("1");
                    Log.e("Flag", arrData[1]);
                    Intent intent = new Intent(getActivity(), Login_activity.class);
                    intent.putExtra("State", "new");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
        return root;
    }

   /* private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }*/

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }
}