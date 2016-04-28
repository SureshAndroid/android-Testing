package app.testingappnew;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ASUS on 20-Apr-16.
 */
public class Fragment_3 extends Fragment {

    Context context;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_3,container,false);
        context = getActivity();
        btn = (Button) rootView.findViewById(R.id.button_fragment_3);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Fragment 3 . . .",Toast.LENGTH_SHORT);
            }

        });

        return rootView;
    }
}
