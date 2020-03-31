package ly.bsagar.bakingapp.Fragments;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DetailStepFragment extends Fragment {
    FragmentDetailStepBinding binding ;
    BakingViewModel model;
    Context context;
    SimpleExoPlayer player;
    int mResumeWindow = -1;
    long mResumePosition = -1;

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
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "restart");
        player = new SimpleExoPlayer.Builder(context).build();
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        binding.playerView.setPlayer(player);
        binding.stepDetail.setText(model.getStep().getShorDescription());
        Log.d(TAG, "onResume: " + "restart" + model.getStep().getVideoURL());
        if (model.getStep().getVideoURL().isEmpty()){
            if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                    getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE){
                binding.playerView.setVisibility(View.GONE);
                binding.playerView.setVisibility(View.VISIBLE);
            }
        } else {
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, "bakingapp"));
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(Uri.parse(model.getStep().getVideoURL()));
            player.setPlayWhenReady(true);
            player.prepare(videoSource);
            if (mResumeWindow != -1) {
                player.seekTo(mResumeWindow,mResumePosition);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mResumeWindow = player.getCurrentWindowIndex();
        mResumePosition = Math.max(0, player.getContentPosition());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mResumeWindow",mResumeWindow);
        outState.putLong("mResumePosition",mResumePosition);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
