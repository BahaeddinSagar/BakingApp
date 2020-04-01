package ly.bsagar.bakingapp.Fragments;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import ly.bsagar.bakingapp.BakingViewModel;
import ly.bsagar.bakingapp.databinding.FragmentDetailStepBinding;


public class DetailStepFragment extends Fragment {
    FragmentDetailStepBinding binding ;
    BakingViewModel model;
    Context context;
    SimpleExoPlayer player;
    int mResumeWindow = -1;
    long mResumePosition = -1;
    private long playbackPosition;
    private int currentWindow;

    public DetailStepFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailStepBinding.inflate(inflater);
        context = container.getContext();
        if (savedInstanceState != null) {
            mResumeWindow = savedInstanceState.getInt("mResumePosition");
            mResumePosition = savedInstanceState.getLong("mResumePosition");
        }
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (Util.SDK_INT >= 24 && !model.getStep().getVideoURL().isEmpty() ) {
            intilizePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.stepDetail.setText(model.getStep().getShorDescription());
        if (model.getStep().getVideoURL().isEmpty()){
            if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                    getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE){
                binding.playerView.setVisibility(View.GONE);
                binding.playerView.setVisibility(View.VISIBLE);
            }
        } else {
            if ((Util.SDK_INT < 24 || player == null)) {
                intilizePlayer();
            }
        }
    }

    private void intilizePlayer() {
        player = new SimpleExoPlayer.Builder(context).build();
        binding.playerView.setPlayer(player);
        MediaSource mediaSource = buildMediaSource(Uri.parse(model.getStep().getVideoURL()));
        player.setPlayWhenReady(true);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }
    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "bakingapp");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        mResumeWindow = player.getCurrentWindowIndex();
        mResumePosition = Math.max(0, player.getContentPosition());
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {

            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mResumeWindow",mResumeWindow);
        outState.putLong("mResumePosition",mResumePosition);
    }
}
