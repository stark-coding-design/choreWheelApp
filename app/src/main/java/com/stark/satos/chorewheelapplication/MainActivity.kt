package com.stark.satos.chorewheelapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class MainActivity : AppCompatActivity() {

    private var degree = 0
    private var isSpinning = false
    private var wheel: ImageView? = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout: ConstraintLayout? = findViewById(R.id.constraint_layout)
        wheel = findViewById(R.id.spinningWheel)
        layout?.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onCLick() {
                super.onClick()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onDoubleClick() {
                super.onDoubleClick()
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
            }

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                if (!isSpinning) {
                    spin()
                    isSpinning = true
                }
                return super.onTouch(view, motionEvent)
            }
        })
        degreeeForSectors
    }

    private val degreeeForSectors: Unit
        get() {
            val sectorDegree = 360 / sectors.size
            for (i in sectors.indices) {
                sectorDegrees[i] = (i + 1) * sectorDegree
            }
        }

    private fun spin() {
        degree = random.nextInt(sectors.size - 1)
        val rotateAnimation = RotateAnimation(
            0F, (360 * sectors.size + sectorDegrees[degree]).toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 3600
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                Toast.makeText(this@MainActivity,"You Get " + sectors[sectors.size - (degree + 1)] + "!!", Toast.LENGTH_LONG).show()
                isSpinning = false
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        wheel!!.startAnimation(rotateAnimation)
    }

    companion object {
        private val sectors = arrayOf(
            "To Dust The Picture Frames",
            "To Put The Away Groceries",
            "To Pick Your Own Chore From The List",
            "To Put Away The Dry Dishes",
            "To Sweep The Porch",
            "To Wash Some Clothes",
            "To Set And Then Clear The Dinner Table",
            "To Take Care Of The Animals",
            "To Dust The Furniture",
            "To Take A Little Break",
            "To Take Out The Trash",
            "To Have Your Parent Choose Your Next Chore",
            "To Clean The Windows",
            "To Clean The Bathrooms",
            "To Fold Clothes",
            "To Sweep The House",
            "To Wash The Dishes",
            "To Mop The Floors",
            "To Vacuum",
            "To Help Wth Dinner",
            "To Yard Work",
            "To Clean Family Room",
            "To Have Your Parent Choose Your Next Chore",
            "To Clean The Kitchen"
        )
        private val sectorDegrees = IntArray(sectors.size)
        private val random = Random()
    }
}