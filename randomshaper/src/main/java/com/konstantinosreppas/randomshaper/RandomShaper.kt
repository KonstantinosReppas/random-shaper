package com.konstantinosreppas.randomshaper

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import kotlin.math.*

class RandomShaper(private val color: Int = Color.BLACK, private val points: Int = 4) {

    private var shapeSize = 0

    fun setupShape(image: ImageView) {

        image.width { shapeSize = it

            val coordinates = getCoordinatesForScale()

            val px = arrayListOf<Int>()
            val py = arrayListOf<Int>()
            for (c in coordinates) {
                px.add(c.second)
                py.add(c.first)
            }

            val p = Polygon(
                px.toIntArray(),
                py.toIntArray(),
                px.size
            )

            val myBitmap = createPlaceholderImage(shapeSize)

            val pixels = IntArray(shapeSize * shapeSize)
            myBitmap.getPixels(
                pixels,
                0,
                myBitmap.width,
                0,
                0,
                myBitmap.width,
                myBitmap.height
            )

            for (i in 0 until shapeSize) {
                for (j in 0 until shapeSize) {
                    if (p.contains(j.toFloat(), i.toFloat())) {
                        pixels[shapeSize * i + j] = color
                    } else {
                        pixels[shapeSize * i + j] = Color.TRANSPARENT
                    }
                }
            }


            myBitmap.setPixels(
                pixels,
                0,
                myBitmap.width,
                0,
                0,
                myBitmap.width,
                myBitmap.height
            )

            image.setImageBitmap(myBitmap)
        }


    }


    private fun getCoordinatesForScale(): ArrayList<Pair<Int, Int>> {
        val coordinates = ArrayList<Pair<Int, Int>>() //y, x

        val x = ArrayList<Int>()
        val y = ArrayList<Int>()


        for (i in 0 until points) {

            var xx = 0
            var yy = 0

            var cont = true

            while (cont) {
                xx = (0..shapeSize).random()
                yy = (0..shapeSize).random()

                //checking to see if point is not that close to an existing.
                if (x.size > 0)
                    for (j in 0 until x.size) {
                        if (sqrt(
                                ((xx - x[j]).absoluteValue).toDouble().pow(2) +
                                        ((yy - y[j]).absoluteValue).toDouble().pow(2)
                            ) < shapeSize * 0.1f
                        ) {
                            cont = true
                            break
                        } else {
                            cont = false
                        }
                    }
                else {
                    cont = false
                }

            }
            x.add(xx)
            y.add(yy)

        }

        val ps = ArrayList<Polygon.Point>()

        for (i in 0 until points) {
            ps.add(Polygon.Point(x[i], y[i]))
        }

        Polygon.sortVertices(ps)
        for (i in 0 until ps.size) {
            coordinates.add(
                Pair(
                    ps[i].y,
                    ps[i].x
                )
            )
        }


        return coordinates
    }

    private fun createPlaceholderImage(size: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        paint.color = Color.CYAN
        canvas.drawRect(0f, 0f, size.toFloat(), size.toFloat(), paint)
        return bitmap
    }

    private fun <T : View> T.width(function: (Int) -> Unit) {
        if (width == 0)
            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    function(width)
                }
            })
        else function(width)
    }

}