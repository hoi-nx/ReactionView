package com.mteam.reactionview

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.facebook.keyframes.KeyframesDrawableBuilder
import com.facebook.keyframes.deserializers.KFImageDeserializer
import kotlinx.android.synthetic.main.react_view.view.*


class ReactionAnimation {

    fun react(context: Context, view: View) {
        val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.react_view, null,false)

        val popupWindow = PopupWindow(
            layout,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true)
        popupWindow.animationStyle = R.style.PopupAnimation
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        
        val streamlike =context.resources.assets.open("Like.json")
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

        val imageViewlike = layout.imgLike
        val imageViewlove = layout.imgLove
        val imageViewlaugh = layout.imgLaugh
        val imageViewwow = layout.imgWow
        val imageViewsad = layout.imgSad
        val imageViewangry = layout.imgAngry


        imageViewlike.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewlike.setImageDrawable(kfDrawablelike)
        imageViewlike.imageAlpha = 0

        imageViewlove.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewlove.setImageDrawable(kfDrawablelove)
        imageViewlove.imageAlpha = 0

        imageViewlaugh.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewlaugh.setImageDrawable(kfDrawablelaugh)
        imageViewlaugh.imageAlpha = 0

        imageViewwow.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewwow.setImageDrawable(kfDrawablewow)
        imageViewwow.imageAlpha = 0

        imageViewsad.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewsad.setImageDrawable(kfDrawablesad)
        imageViewsad.imageAlpha = 0

        imageViewangry.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageViewangry.setImageDrawable(kfDrawableangry)
        imageViewangry.imageAlpha = 0


        if (popupWindow.isShowing) {
            popupWindow.dismiss()
        } else {
            val location = locateView(view)
            popupWindow.showAtLocation(view, Gravity.TOP or Gravity.CENTER, location!!.left - 150,
                location.top - 130)
            kfDrawablelike.startAnimation()
            kfDrawablelove.startAnimation()
            kfDrawablelaugh.startAnimation()
            kfDrawablewow.startAnimation()
            kfDrawablesad.startAnimation()
            kfDrawableangry.startAnimation()


        }

    }


    private fun locateView(v: View?): Rect? {
        val loc_int = IntArray(2)
        if (v == null) return null
        try {
            v.getLocationOnScreen(loc_int)
        } catch (npe: NullPointerException) {
            return null
        }

        val location = Rect()
        location.left = loc_int[0]
        location.top = loc_int[1]
        location.right = location.left + v.width
        location.bottom = location.top + v.height
        return location
    }



}