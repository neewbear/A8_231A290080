package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsDetailFragment extends Fragment {

    public NewsDetailFragment() {
        super(R.layout.fragment_news_detail);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTitle = view.findViewById(R.id.tvDetailTitle);
        TextView tvContent = view.findViewById(R.id.tvDetailContent);
        Button btnRead = view.findViewById(R.id.btnMarkRead);

        Bundle args = getArguments();
        int position = -1;

        if (args != null) {
            tvTitle.setText(args.getString("title", "Chi tiết tin tức"));
            tvContent.setText(args.getString("content", "Nội dung tin tức"));
            position = args.getInt("position", -1);
        }

        final int newsPosition = position;

        btnRead.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putBoolean("isRead", true);
            result.putInt("position", newsPosition);

            getParentFragmentManager().setFragmentResult(
                    "newsRead",
                    result
            );

            getParentFragmentManager().popBackStack();
        });
    }
}
