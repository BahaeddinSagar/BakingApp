package ly.bsagar.bakingapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import ly.bsagar.bakingapp.BakingViewModel;
import ly.bsagar.bakingapp.POJO.Step;
import ly.bsagar.bakingapp.databinding.FragmentDetailStepBinding;


public class DetailStepFragment extends Fragment {
    FragmentDetailStepBinding binding ;
    BakingViewModel model;
    Context context;
    SimpleExoPlayer player;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailStepBinding.inflate(inflater);
        context = container.getContext();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        player = new SimpleExoPlayer.Builder(context).build();
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        binding.playerView.setPlayer(player);
        binding.stepDetail.setText(model.getStep().getShorDescription());
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "bakingapp"));
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(model.getStep().getVideoURL()));
        player.setPlayWhenReady(true);
        player.prepare(videoSource);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
