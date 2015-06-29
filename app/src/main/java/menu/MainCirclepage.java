package menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.cm_smarthome.materialdesignsupportlibrary.R;

import login.Login_activity;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by AdminPond on 27/6/2558.
 */
public class MainCirclepage extends FragmentActivity {
    MyPageAdapter adapter;
    ViewPager pager;
    Context context = this;
    Sqlite sqlite = new Sqlite(context);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_circlepage);

        sqlite.getWritableDatabase();
        sqlite.InsertData();

        String[] arrData = sqlite.SelectData("1");
        if (arrData[0].length() > 0) {
            String Flag = arrData[1];
            String Flag1 = arrData[3];
            Log.e("Flag", arrData[1] + "+" + arrData[3]);
            if (Flag.equals("1") && Flag1.equals("0")) {
                Intent intent = new Intent(context, Login_activity.class);
                intent.putExtra("State", "new");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //String[] arrData = sqlite.SelectData("1");
                Log.e("Flag", arrData[1] + "+" + arrData[3]);
            } else if (Flag.equals("1") && Flag1.equals("1")) {
                Intent intent = new Intent(context, Login_activity.class);
                intent.putExtra("State", "check");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Log.e("Flag", arrData[1] + "+" + arrData[2]);
            }
        }

        adapter = new MyPageAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }
}
