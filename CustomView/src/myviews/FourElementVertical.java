package myviews;

import com.example.customview.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FourElementVertical extends ViewGroup {

	private final TextView mProfileNameText;
	private final TextView mAuthorText;
	private final TextView mMessageText;
	private final TextView mPostText;

	public FourElementVertical(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// public FourElementVertical(Context context) {
	// super(context);
	// // TODO Auto-generated constructor stub
	// }

	public FourElementVertical(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		LayoutInflater.from(context).inflate(R.layout.custom_view1, this, true);
		mProfileNameText = (TextView) findViewById(R.id.text1);
		mAuthorText = (TextView) findViewById(R.id.text2);
		mMessageText = (TextView) findViewById(R.id.text3);
		mPostText = (TextView) findViewById(R.id.text4);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		// int itemWidth = (r - l) / 4;

		int weight[] = { 4, 1, 2, 3 };

		// int heightUsed = 0;

		final int count = getChildCount();
		int curWidth, curHeight, curLeft, curTop, maxHeight;

		// get the available size of child view
		int childLeft = this.getPaddingLeft();
		int childTop = this.getPaddingTop();
		int childRight = this.getMeasuredWidth() - this.getPaddingRight();
		int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
		int childWidth = childRight - childLeft;
		int childHeight = childBottom - childTop;

		Log.e("TAG", "childLeft:" + childLeft);
		Log.e("TAG", "childTop:" + childTop);
		Log.e("TAG", "childRight:" + childRight);
		Log.e("TAG", "childBottom:" + childBottom);
		Log.e("TAG", "childWidth:" + childWidth);
		Log.e("TAG", "childHeight:" + childHeight);
		Log.e("TAG", "count:" + count);

		maxHeight = 0;
		curLeft = childLeft;
		curTop = childTop;

		for (int i = 0; i < 4; i++) {
			final View child = getChildAt(i);

			if (child.getVisibility() != GONE) {
				// Get the maximum size of the child

				int whighetdHight = (childHeight / 10) * weight[i];

				Log.e("TAG", "height calculated: < " + i + " >" + whighetdHight);

				child.measure(MeasureSpec.makeMeasureSpec(childWidth,
						MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
						whighetdHight, MeasureSpec.EXACTLY));

				curWidth = child.getMeasuredWidth();
				curHeight = child.getMeasuredHeight();
				// wrap is reach to the end

				// do the layout
				child.layout(curLeft, curTop, curLeft + curWidth, curTop
						+ curHeight);
				// // store the max height
				// if (maxHeight < curHeight)
				// maxHeight = curHeight;
				curTop += curHeight;

				Log.e("TAG", "curTop:" + curTop);
				Log.e("TAG", "curWidth:" + curWidth);
				Log.e("TAG", "curHeight:" + curHeight);
			}
		}

		// final int paddingLeft = getPaddingLeft();
		// final int paddingTop = getPaddingTop();
		// int currentTop = paddingTop;
		// layoutView(mProfileNameText, paddingLeft, currentTop,
		// mProfileNameText.getMeasuredWidth(),
		// mProfileNameText.getMeasuredHeight());
		// final int contentLeft = getWidthWithMargins(mProfileImage)
		// + paddingLeft;
		// final int contentWidth = r - l - contentLeft - getPaddingRight();
		// layoutView(mAuthorText, contentLeft, currentTop, contentWidth,
		// mAuthorText.getMeasuredHeight());
		// currentTop += getHeightWithMargins(mAuthorText);
		// layoutView(mMessageText, contentLeft, currentTop, contentWidth,
		// mMessageText.getMeasuredHeight());
		// currentTop += getHeightWithMargins(mMessageText);
		// if (mPostImage.getVisibility() != View.GONE) {
		// layoutView(mPostImage, contentLeft, currentTop, contentWidth,
		// mPostImage.getMeasuredHeight());
		// currentTop += getHeightWithMargins(mPostImage);
		// }

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// At this time we need to call setMeasuredDimensions(). Lets just
		// call the parent View's method
		// (see
		// https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/View.java)
		// that does:
		// setMeasuredDimension(getDefaultSize(
		// getSuggestedMinimumWidth(), widthMeasureSpec),
		// getDefaultSize(
		// getSuggestedMinimumHeight(), heightMeasureSpec));
		//

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int wspec = MeasureSpec.makeMeasureSpec(getMeasuredWidth()
				/ getChildCount(), MeasureSpec.EXACTLY);
		int hspec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(),
				MeasureSpec.EXACTLY);
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.measure(wspec, hspec);
		}
	}

}
