package com.pavankumarpatruni.alpabetavatar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import kotlin.math.round

class AlphabetAvatar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private var borderWidth: Float = 5F
    private var borderColor: Int = Color.LTGRAY
    private var bgColor: Int = Color.DKGRAY
    private var textColor: Int = Color.WHITE

    private val backgroundPaint: Paint = Paint()
    private val borderPaint: Paint = Paint()
    private val textPaint: Paint = Paint()

    private val bounds: RectF = RectF()
    private val borderBounds: RectF = RectF()
    private val textBounds: Rect = Rect()

    private var name: String = String()
    private var initial: String = String()

    private var width: Float = 0F
    private var height: Float = 0F

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphabetAvatar)

            bgColor = typedArray.getColor(
                R.styleable.AlphabetAvatar_background_color, Color.BLUE
            )

            borderColor = typedArray.getColor(
                R.styleable.AlphabetAvatar_border_color,
                Color.GRAY
            )

            borderWidth = typedArray.getDimension(
                R.styleable.AlphabetAvatar_border_width,
                5F
            )

            textColor = typedArray.getColor(
                R.styleable.AlphabetAvatar_text_color,
                Color.WHITE
            )

            name = typedArray.getNonResourceString(R.styleable.AlphabetAvatar_text)

            typedArray.recycle()
        }
    }

    private fun prepareBackgroundPaint() {
        backgroundPaint.isAntiAlias = true
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.color = bgColor
    }

    private fun prepareStrokePaint() {
        borderPaint.isAntiAlias = true
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = borderColor
        borderPaint.strokeWidth = borderWidth
    }

    private fun prepareTextPaint() {
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 100F
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.color = textColor
    }

    private fun drawUI() {
        if (getWidth() == 0 && getHeight() == 0) {
            return
        }

        prepareBackgroundPaint()
        prepareTextPaint()
        prepareStrokePaint()

        invalidate()
    }

    fun setBorderColor(borderColor: Int) {
        borderPaint.color = borderColor
        this.borderColor = borderColor

        invalidate()
    }

    fun setBorderWidth(borderWidth: Int) {
        borderPaint.strokeWidth = borderWidth.toFloat()
        this.borderWidth = borderWidth.toFloat()

        invalidate()
    }

    fun setBGColor(backgroundColor: Int) {
        backgroundPaint.color = backgroundColor
        this.bgColor = backgroundColor

        invalidate()
    }

    fun setTextColor(textColor: Int) {
        textPaint.color = textColor
        this.textColor = textColor

        invalidate()
    }

    fun setText(name: String) {
        this.name = name

        drawUI()
    }

    private fun getInitial(): String {
        return name[0].toString()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawUI()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)

        drawUI()
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        super.setPaddingRelative(start, top, end, bottom)

        drawUI()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        this.width = measuredWidth.toFloat() - this.paddingLeft - this.paddingRight
        this.height = measuredHeight.toFloat() - this.paddingTop - this.paddingBottom
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        bounds.set(0F, 0F, width, height)

        canvas?.drawOval(bounds, backgroundPaint)

        borderBounds.set(
            borderPaint.strokeWidth / 2,
            borderPaint.strokeWidth / 2,
            width - borderPaint.strokeWidth / 2,
            height - borderPaint.strokeWidth / 2
        )
        canvas?.drawOval(borderBounds, borderPaint)

        initial = getInitial()
        textPaint.getTextBounds(initial, 0, initial.length, textBounds)

        val textBottom = round((bounds.height() / 2F) + (textBounds.height() / 2F))
        canvas?.drawText(initial, width / 2F, textBottom, textPaint)
    }

}
