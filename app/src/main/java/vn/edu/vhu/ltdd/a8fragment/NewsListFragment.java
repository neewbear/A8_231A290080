package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsListFragment extends Fragment {

    private final String[] titles = {
            "Thông báo tuyển sinh VHU",
            "Hoạt động sinh viên",
            "Tin tức Đại học Văn Hiến"
    };

    private final boolean[] readStatus = new boolean[3];

    public NewsListFragment() {
        super(R.layout.fragment_news_list);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout container = view.findViewById(R.id.newsContainer);

        if (container == null) {
            return;
        }

        getParentFragmentManager().setFragmentResultListener(
                "newsRead",
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    boolean isRead = result.getBoolean("isRead", false);
                    int position = result.getInt("position", -1);

                    if (isRead && position >= 0 && position < readStatus.length) {
                        readStatus[position] = true;
                        showNews(container);
                    }
                }
        );

        showNews(container);
    }

    private void showNews(LinearLayout container) {
        container.removeAllViews();

        for (int i = 0; i < titles.length; i++) {
            final int position = i;

            Button button = new Button(requireContext());
            String status = readStatus[i] ? " (Đã đọc)" : "";
            button.setText(titles[i] + status);
            button.setAllCaps(false);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(8, 8, 8, 8);
            container.addView(button, params);

            button.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("title", titles[position]);
                bundle.putString(
                        "content",
                        "Đây là nội dung chi tiết của tin: "
                                + titles[position]
                );
                bundle.putInt("position", position);

                NewsDetailFragment fragment = new NewsDetailFragment();
                fragment.setArguments(bundle);

                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            });
        }
    }
}
