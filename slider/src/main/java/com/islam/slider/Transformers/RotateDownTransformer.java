package com.islam.slider.Transformers;

import android.view.View;

import androidx.core.view.ViewCompat;


public class RotateDownTransformer extends BaseTransformer {

	private static final float ROT_MOD = -15f;

	@Override
	protected void onTransform(View view, float position) {
		final float width = view.getWidth();
		final float height = view.getHeight();
		final float rotation = ROT_MOD * position * -1.25f;

		ViewCompat.setPivotX(view,width * 0.5f);
        ViewCompat.setPivotY(view,height);
        ViewCompat.setRotation(view,rotation);
	}
	
	@Override
	protected boolean isPagingEnabled() {
		return true;
	}

}
