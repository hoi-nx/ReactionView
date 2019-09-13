package com.mteam.reactionview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.facebook.keyframes.KeyframesDrawableBuilder
import com.facebook.keyframes.deserializers.KFImageDeserializer

@SuppressLint("ViewConstructor")
class ReactionView constructor(
    context: Context,
    val reaction: Reaction
) : ImageView(context) {

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
        //initAnimation(context)


    }

    fun initAnimation(context: Context) {
        val streamlike = context.resources.assets.open("Like.json")
        val streamlove = context.resources.assets.open("Love.json")
        val streamlaugh = context.resources.assets.open("Haha.json")
        val streamwow = context.resources.assets.open("Wow.json")
        val streamsad = context.resources.assets.open("Sorry.json")
        val streamangry = context.resources.assets.open("Anger.json")

        val kfImagelike = KFImageDeserializer.deserialize(streamlike)
        val kfImagelove = KFImageDeserializer.deserialize(streamlove)
        val kfImagelaugh = KFImageDeserializer.deserialize(streamlaugh)
        val kfImagewow = KFImageDeserializer.deserialize(streamwow)
        val kfImagesad = KFImageDeserializer.deserialize(streamsad)
        val kfImageangry = KFImageDeserializer.deserialize(streamangry)
        val kfDrawablelike = KeyframesDrawableBuilder().withImage(kfImagelike).build()
        val kfDrawablelove = KeyframesDrawableBuilder().withImage(kfImagelove).build()
        val kfDrawablelaugh = KeyframesDrawableBuilder().withImage(kfImagelaugh).build()
        val kfDrawablewow = KeyframesDrawableBuilder().withImage(kfImagewow).build()
        val kfDrawablesad = KeyframesDrawableBuilder().withImage(kfImagesad).build()
        val kfDrawableangry = KeyframesDrawableBuilder().withImage(kfImageangry).build()

        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageAlpha = 0
        when (reaction.type) {
            R.drawable.ic_fb_like -> {
                setImageDrawable(kfDrawablelike)
            }
            R.drawable.ic_fb_love -> {
                setImageDrawable(kfDrawablelove)
            }
            R.drawable.ic_fb_laugh -> {
                setImageDrawable(kfDrawablelaugh)
            }
            R.drawable.ic_fb_angry -> {
                setImageDrawable(kfDrawableangry)
            }

            R.drawable.ic_fb_sad -> {
                setImageDrawable(kfDrawablesad)
            }
            R.drawable.ic_fb_wow -> {
                setImageDrawable(kfDrawablewow)
            }

        }
        kfDrawablelike.startAnimation()
        kfDrawablelove.startAnimation()
        kfDrawablelaugh.startAnimation()
        kfDrawablewow.startAnimation()
        kfDrawablesad.startAnimation()
        kfDrawableangry.startAnimation()
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        location.set(0, 0)
    }
}
