package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    public static ProfileFragment newInstance(String name, String studentId) {
        ProfileFragment fragment = new ProfileFragment();

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("studentId", studentId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvStudentId = view.findViewById(R.id.tvStudentId);

        Bundle args = getArguments();

        if (args != null) {
            tvName.setText(args.getString("name", "Nguyễn Văn A"));
            tvStudentId.setText(args.getString("studentId", "2201234567"));
        }
    }
}
