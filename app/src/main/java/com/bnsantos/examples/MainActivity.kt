package com.bnsantos.examples

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.PropertyValuesHolder


class MainActivity : AppCompatActivity() {

    private var useXML = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        animationModeCheckBox.setOnCheckedChangeListener { _, checked ->
            useXML = checked
        }

        fadeOutBtn.setOnClickListener {
            if (useXML) {
                textView.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_out))
            } else {
                val animation = AlphaAnimation(1f, 0f)
                animation.duration = 1000
                animation.interpolator = AccelerateInterpolator()
                textView.startAnimation(animation)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {}

                    override fun onAnimationEnd(p0: Animation?) {
                        textView.alpha = 0f
                    }

                    override fun onAnimationStart(p0: Animation?) {}
                })
            }
        }

        fadeInBtn.setOnClickListener {
            if (useXML) {
                textView.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_in))
            } else {
                val animation = AlphaAnimation(0f, 1f)
                animation.duration = 1000
                animation.interpolator = AccelerateInterpolator()
                textView.startAnimation(animation)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {}

                    override fun onAnimationEnd(p0: Animation?) {}

                    override fun onAnimationStart(p0: Animation?) {
                        textView.alpha = 1f
                    }
                })
            }
        }

        zoomInBtn.setOnClickListener {
            if (useXML) {
                val loadAnimation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.zoom_in)
                loadAnimation.duration = 500
                textView.startAnimation(loadAnimation)
            } else {
                ObjectAnimator.ofPropertyValuesHolder(textView,
                        PropertyValuesHolder.ofFloat("scaleX", 1f, 3f),
                        PropertyValuesHolder.ofFloat("scaleY", 1f, 3f)).apply {

                    duration = 1000
                    start()
                }

               /* val scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 3f).apply {
                    duration = 1000
                }

                val scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 3f).apply {
                    duration = 1000
                }

                val animatorSet = AnimatorSet()
                animatorSet.playTogether(scaleX, scaleY)
                animatorSet.start()*/
            }
        }

        zoomOutBtn.setOnClickListener {
            textView.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.zoom_out))
        }

        rotateBtn.setOnClickListener {
            textView.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate))
        }

        clearBtn.setOnClickListener {
            textView.clearAnimation()
        }

    }
}
