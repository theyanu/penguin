package me.yanu.penguin;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


public class PenguinFragment extends Fragment {
    private static final String ARG_TEXT = "var_name";
    private static final String ARG_COLOR = "var_color";
    private static final String ARG_ANIMATION = "var_animation";
    private static final String ARG_LOOP = "var_loop";

    // TODO: Rename and change types of parameters
    private String mText;
    private String mAnimation;
    private int mColor;
    private boolean mLoop;

    private OnFragmentInteractionListener mListener;

    private FrameLayout mContent;
    private LottieAnimationView mAnimationView;
    private TextView mTextView;

    public PenguinFragment() {
        // Required empty public constructor
    }

    public static PenguinFragment newInstance(String name, int color, String animation, boolean loop) {
        PenguinFragment fragment = new PenguinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, name);
        args.putInt(ARG_COLOR, color);
        args.putString(ARG_ANIMATION, animation);
        args.putBoolean(ARG_LOOP, loop);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(ARG_TEXT);
            mColor = getArguments().getInt(ARG_COLOR);
            mAnimation = getArguments().getString(ARG_ANIMATION);
            mLoop = getArguments().getBoolean(ARG_LOOP);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mText = args.getString(ARG_TEXT);
            mColor = args.getInt(ARG_COLOR);
            mAnimation = args.getString(ARG_ANIMATION);
            mLoop = args.getBoolean(ARG_LOOP);
        } else {
            mText = savedInstanceState.getString(ARG_TEXT);
            mColor = savedInstanceState.getInt(ARG_COLOR);
            mAnimation = savedInstanceState.getString(ARG_ANIMATION);
            mLoop = savedInstanceState.getBoolean(ARG_LOOP);
        }

        mTextView = (TextView) view.findViewById(R.id.text_view);
        mTextView.setText(mText);

        mContent = (FrameLayout) view.findViewById(R.id.content);
        mContent.setBackgroundColor(ContextCompat.getColor(getContext(), mColor));

        mAnimationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
        mAnimationView.setAnimation(mAnimation);
        mAnimationView.loop(mLoop);

        if (mLoop) {
            mAnimationView.playAnimation();
        } else {
            mAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAnimationView.getProgress() == 1.0)
                        mAnimationView.reverseAnimation();
                    else
                        mAnimationView.playAnimation();
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_penguin, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
