package org.groebl.sms.ui.welcome;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.groebl.sms.R;
import org.groebl.sms.ui.ThemeManager;
import org.groebl.sms.ui.view.colorpicker.ColorPickerPalette;
import org.groebl.sms.ui.view.colorpicker.ColorPickerSwatch;

public class WelcomeThemeFragment extends BaseWelcomeFragment implements ColorPickerSwatch.OnColorSelectedListener, BaseWelcomeFragment.WelcomeScrollListener {
    private final String TAG = "WelcomeThemeFragment";

    private ColorPickerPalette mPallette;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_theme, container, false);

        mPallette = (ColorPickerPalette) view.findViewById(R.id.welcome_themes);
        mPallette.init(19, 4, this);
        mPallette.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        mPallette.drawPalette(ThemeManager.PALETTE, ThemeManager.getColor());

        return view;
    }

    @Override
    public void onColorSelected(int color) {
        ThemeManager.setColor(mContext, color);
        mPallette.drawPalette(ThemeManager.PALETTE, color);
    }

    @Override
    public void onScrollOffsetChanged(WelcomeActivity activity, float offset) {
        if (mPallette != null) {
            offset = (float) Math.pow(1 - offset, 4);
            int alpha = (int) (255 * 0.6 * offset);
            mPallette.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        }
    }
}
