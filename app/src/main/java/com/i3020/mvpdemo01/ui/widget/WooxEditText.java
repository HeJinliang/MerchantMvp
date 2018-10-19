/*    
 * Company:  杭州洞见科技有限公司（www.i3020.com）
 * 
 * How much you pay, how much you will reap!
 * Nobody can casually succeed without working hard！
 *
 */
package com.i3020.mvpdemo01.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Describe:  自定义带一键清除的EditText
 * <p/>
 * Author:  Mr.Woox Hunter
 * Time:    2016-02-04  11:27
 */
public class WooxEditText extends AppCompatEditText {
    private Drawable mRightDrawable;
    private boolean isHasFocus;
    private boolean resetText;
    private int cursorPos;
    private String inputAfterText;
    private Context context;

    public WooxEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public WooxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public WooxEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        //getCompoundDrawables:
        //Returns drawables for the left, top, right, and bottom borders.
        Drawable[] drawables = this.getCompoundDrawables();

        //取得right位置的Drawable
        //即我们在布局文件中设置的android:drawableRight
        mRightDrawable = drawables[2];


        //设置焦点变化的监听
        setOnFocusChangeListener(new FocusChangeListenerImpl());

        //初始化时让右边clean图标不可见
        setClearDrawableVisible(false);
    }


    /**
     * 当手指抬起的位置在clean的图标的区域
     * 我们将此视为进行清除操作
     * getWidth():得到控件的宽度
     * event.getX():抬起时的坐标(改坐标是相对于控件本身而言的)
     * getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
     * getPaddingRight():clean的图标右边缘至控件右边缘的距离
     * 于是:
     * getWidth() - getTotalPaddingRight()表示:
     * 控件左边到clean的图标左边缘的区域
     * getWidth() - getPaddingRight()表示:
     * 控件左边到clean的图标右边缘的区域
     * 所以这两者之间的区域刚好是clean的图标的区域
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight())) &&
                        (event.getX() < (getWidth() - getPaddingRight()));
                if (isClean) {
                    setText("");
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class FocusChangeListenerImpl implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText text = (EditText) v;
            isHasFocus = hasFocus;
            if (isHasFocus) {
                boolean isVisible = getText().toString().length() >= 1;
                setClearDrawableVisible(isVisible);

//                try {
//                    String hint = text.getHint().toString();
//                    text.setTag(hint);
//                    text.setHint("");
//                } catch (Exception e) {
//                }
                //设置EditText文字变化的监听
                addTextChangedListener(new TextWatcherImpl());

            } else {
                setClearDrawableVisible(false);

//                try {
//                    String hint = text.getTag().toString();
//                    text.setHint(hint);
//                } catch (Exception e) {
//                }
            }
        }

    }

    //当输入结束后判断是否显示右边clean的图标
    private class TextWatcherImpl implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (!resetText) {
                cursorPos = getSelectionEnd();                    // 这里用s.toString()而不直接用s是因为如果用s，
                // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
                // inputAfterText也就改变了，那么表情过滤就失败了
                inputAfterText = s.toString();
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if (!resetText) {
                    if (count >= 2) {//表情符号的字符长度最小为2
                        CharSequence input = s.subSequence(cursorPos, cursorPos + count);
                        if (containsEmoji(input.toString())) {
                            resetText = true;
                            Toast.makeText(context, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();                            //是表情符号就将文本还原为输入表情符号之前的内容
                            setText(inputAfterText);
                            CharSequence text = getText();
                            if (text instanceof Spannable) {
                                Spannable spanText = (Spannable) text;
                                Selection.setSelection(spanText, text.length());
                            }
                        }
                    }
                } else {
                    resetText = false;
                }
            } catch (Exception e) {

            }
            Editable text = getText();
            if (text.length() == 0) {
                setClearDrawableVisible(false);
            } else {
                setClearDrawableVisible(true);
            }

        }

    }

    public boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    private boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    //隐藏或者显示右边clean的图标
    public void setClearDrawableVisible(boolean isVisible) {
        Drawable rightDrawable;
        if (isVisible) {
            rightDrawable = mRightDrawable;
        } else {
            rightDrawable = null;
        }
        //使用代码设置该控件left, top, right, and bottom处的图标
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);
    }

    // 显示一个动画,以提示用户输入
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    //CycleTimes动画重复的次数
    public Animation shakeAnimation(int CycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

}