package smog_check.vihan.com.smog_cheching;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * home1
 * @author andye
 */
public class Fra_helper extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fra_helper,container, false);
        init(v);

        return v;
    }

    private void init(View v) {

        // headerIV = (ImageView) v.findViewById(R.id.person2_header_iv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // case R.id.person2_shopping: // 按钮操作
            // break;

            default:
                break;
        }
    }

}
