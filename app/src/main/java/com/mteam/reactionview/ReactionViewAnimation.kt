package com.mteam.reactionview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.facebook.keyframes.KeyframesDrawableBuilder
import com.facebook.keyframes.deserializers.KFImageDeserializer

@SuppressLint("ViewConstructor")
class ReactionViewAnimation constructor(
    context: Context,
    val reaction: Reaction
) : LottieAnimationView(context) {

    val location = Point()
        get() {
            if (field.x == 0 || field.y == 0) {
                val location = IntArray(2).also(::getLocationOnScreen)
                field.set(location[0], location[1])
            }
            return field
        }

    init {
        scaleType = reaction.scaleType
        setImageDrawable(ContextCompat.getDrawable(context, reaction.type))
        initAnimation()
    }

    private fun initAnimation() {
        when (reaction.type) {
            R.drawable.ic_fb_like -> {
                setAnimation("like_lt.json")

            }
            R.drawable.ic_fb_love -> {
                setAnimation("like_lt.json")

            }
            R.drawable.ic_fb_laugh -> {
                setAnimation("like_lt.json")

            }
            R.drawable.ic_fb_angry -> {
                setAnimation("like_lt.json")
            }
            R.drawable.ic_fb_sad -> {
                setAnimation("like_lt.json")
            }
            R.drawable.ic_fb_wow -> {
                setAnimation("like_lt.json")
            }
        }
        loop(true)
        playAnimation()

    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        location.set(0, 0)
    }
}
