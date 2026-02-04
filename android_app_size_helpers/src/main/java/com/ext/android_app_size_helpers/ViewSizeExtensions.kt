package com.ext.android_app_size_helpers

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Set view size based on screen percentage
 * @param widthPercent 0.0f to 1.0f or null to keep current width
 * @param heightPercent 0.0f to 1.0f or null to keep current height
 */
fun View.setSizePercent(
    widthPercent: Float? = null,
    heightPercent: Float? = null
) {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    widthPercent?.let {
        params.width = AppSizeHelper.widthPercent(context, it)
    }

    heightPercent?.let {
        params.height = AppSizeHelper.heightPercent(context, it)
    }

    layoutParams = params
}

/**
 * Set width as percentage of screen width
 */
fun View.setWidthPercent(percent: Float) {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = AppSizeHelper.widthPercent(context, percent)
    layoutParams = params
}

/**
 * Set height as percentage of screen height
 */
fun View.setHeightPercent(percent: Float) {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.height = AppSizeHelper.heightPercent(context, percent)
    layoutParams = params
}

/**
 * Set padding based on screen percentage
 */
fun View.setPaddingPercent(
    leftPercent: Float = 0f,
    topPercent: Float = 0f,
    rightPercent: Float = 0f,
    bottomPercent: Float = 0f
) {
    setPadding(
        AppSizeHelper.widthPercent(context, leftPercent),
        AppSizeHelper.heightPercent(context, topPercent),
        AppSizeHelper.widthPercent(context, rightPercent),
        AppSizeHelper.heightPercent(context, bottomPercent)
    )
}

/**
 * Set margin based on screen percentage
 */
fun View.setMarginPercent(
    leftPercent: Float = 0f,
    topPercent: Float = 0f,
    rightPercent: Float = 0f,
    bottomPercent: Float = 0f
) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    params.setMargins(
        AppSizeHelper.widthPercent(context, leftPercent),
        AppSizeHelper.heightPercent(context, topPercent),
        AppSizeHelper.widthPercent(context, rightPercent),
        AppSizeHelper.heightPercent(context, bottomPercent)
    )
    layoutParams = params
}

/**
 * Set text size as percentage of screen width (for responsive text)
 */
fun TextView.setTextSizePercent(percent: Float) {
    val size = AppSizeHelper.widthPercent(context, percent)
    textSize = AppSizeHelper.pxToDp(size)
}

/**
 * Get view width as percentage of screen
 */
fun View.getWidthPercent(): Float {
    return width.toFloat() / AppSizeHelper.screenWidth(context)
}

/**
 * Get view height as percentage of screen
 */
fun View.getHeightPercent(): Float {
    return height.toFloat() / AppSizeHelper.screenHeight(context)
}

/**
 * Set different sizes for portrait and landscape orientations
 */
fun View.setSizePercentOrientation(
    portraitWidth: Float? = null,
    portraitHeight: Float? = null,
    landscapeWidth: Float? = null,
    landscapeHeight: Float? = null
) {
    if (AppSizeHelper.isPortrait(context)) {
        setSizePercent(portraitWidth, portraitHeight)
    } else {
        setSizePercent(landscapeWidth, landscapeHeight)
    }
}

/**
 * Set size with aspect ratio
 * @param widthPercent Width as percentage of screen
 * @param aspectRatio Aspect ratio (use AppSizeHelper.AspectRatio constants)
 */
fun View.setSizeWithAspectRatio(widthPercent: Float, aspectRatio: Float) {
    val width = AppSizeHelper.widthPercent(context, widthPercent)
    val height = AppSizeHelper.calculateAspectHeight(width, aspectRatio)

    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    params.height = height
    layoutParams = params
}

/**
 * Auto-scale text size based on screen category
 */
fun TextView.setAutoScaledTextSize(baseSize: Float) {
    textSize = AppSizeHelper.getResponsiveTextSize(context, baseSize)
}

/**
 * Set size in dp (converted to px automatically)
 */
fun View.setSizeDp(widthDp: Int? = null, heightDp: Int? = null) {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    widthDp?.let {
        params.width = AppSizeHelper.dpToPx(it.toFloat())
    }

    heightDp?.let {
        params.height = AppSizeHelper.dpToPx(it.toFloat())
    }

    layoutParams = params
}

/**
 * Set padding in dp (converted to px automatically)
 */
fun View.setPaddingDp(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    setPadding(
        AppSizeHelper.dpToPx(left.toFloat()),
        AppSizeHelper.dpToPx(top.toFloat()),
        AppSizeHelper.dpToPx(right.toFloat()),
        AppSizeHelper.dpToPx(bottom.toFloat())
    )
}

/**
 * Set margin in dp (converted to px automatically)
 */
fun View.setMarginDp(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    params.setMargins(
        AppSizeHelper.dpToPx(left.toFloat()),
        AppSizeHelper.dpToPx(top.toFloat()),
        AppSizeHelper.dpToPx(right.toFloat()),
        AppSizeHelper.dpToPx(bottom.toFloat())
    )
    layoutParams = params
}

/**
 * Make view square based on width percentage
 */
fun View.setSquareSize(percent: Float) {
    val size = AppSizeHelper.widthPercent(context, percent)
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = size
    params.height = size
    layoutParams = params
}

/**
 * Set view visibility with optional animation
 */
fun View.setVisiblePercent(visible: Boolean, animate: Boolean = false) {
    if (animate) {
        animate()
            .alpha(if (visible) 1f else 0f)
            .setDuration(300)
            .withEndAction {
                visibility = if (visible) View.VISIBLE else View.GONE
            }
            .start()
    } else {
        visibility = if (visible) View.VISIBLE else View.GONE
    }
}

// ========== SCALING EXTENSIONS (Flutter/React Native style) ==========

/**
 * Scale width - .w extension
 * Usage: 100.w(context) returns scaled width in dp
 * Example: button.setScaledSize(widthDp = 200.w(context).toInt())
 */
fun Int.w(context: Context): Float {
    return AppSizeHelper.scaleWidth(context, this.toFloat())
}

fun Float.w(context: Context): Float {
    return AppSizeHelper.scaleWidth(context, this)
}

/**
 * Scale height - .h extension
 * Usage: 50.h(context) returns scaled height in dp
 */
fun Int.h(context: Context): Float {
    return AppSizeHelper.scaleHeight(context, this.toFloat())
}

fun Float.h(context: Context): Float {
    return AppSizeHelper.scaleHeight(context, this)
}

/**
 * Scale font size - .sp extension
 * Usage: textView.setScaledTextSize(16.sp(context))
 */
fun Int.sp(context: Context): Float {
    return AppSizeHelper.scaleFontSize(context, this.toFloat())
}

fun Float.sp(context: Context): Float {
    return AppSizeHelper.scaleFontSize(context, this)
}

/**
 * Scale radius - .r extension
 * Usage: cardView.radius = 8.r(context)
 */
fun Int.r(context: Context): Float {
    return AppSizeHelper.scaleRadius(context, this.toFloat())
}

fun Float.r(context: Context): Float {
    return AppSizeHelper.scaleRadius(context, this)
}

/**
 * Linear scale - .scale extension
 * Usage: 16.scale(context)
 */
fun Int.scale(context: Context): Float {
    return AppSizeHelper.scale(context, this.toFloat())
}

fun Float.scale(context: Context): Float {
    return AppSizeHelper.scale(context, this)
}

/**
 * Vertical scale - .vs extension
 * Usage: 20.vs(context)
 */
fun Int.vs(context: Context): Float {
    return AppSizeHelper.verticalScale(context, this.toFloat())
}

fun Float.vs(context: Context): Float {
    return AppSizeHelper.verticalScale(context, this)
}

/**
 * Moderate scale - .ms extension
 * Usage: 16.ms(context, 0.3f)
 */
fun Int.ms(context: Context, factor: Float = 0.5f): Float {
    return AppSizeHelper.moderateScale(context, this.toFloat(), factor)
}

fun Float.ms(context: Context, factor: Float = 0.5f): Float {
    return AppSizeHelper.moderateScale(context, this, factor)
}

// ========== VIEW EXTENSIONS USING SCALING ==========

/**
 * Set size using scaled values
 * @param widthDp Width in dp (will be scaled)
 * @param heightDp Height in dp (will be scaled)
 */
fun View.setScaledSize(widthDp: Int? = null, heightDp: Int? = null) {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    widthDp?.let {
        params.width = AppSizeHelper.dpToPx(it.w(context))
    }

    heightDp?.let {
        params.height = AppSizeHelper.dpToPx(it.h(context))
    }

    layoutParams = params
}

/**
 * Set padding using scaled values
 * @param left, top, right, bottom Padding in dp (will be scaled)
 */
fun View.setScaledPadding(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    setPadding(
        AppSizeHelper.dpToPx(left.w(context)),
        AppSizeHelper.dpToPx(top.h(context)),
        AppSizeHelper.dpToPx(right.w(context)),
        AppSizeHelper.dpToPx(bottom.h(context))
    )
}

/**
 * Set margin using scaled values
 */
fun View.setScaledMargin(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    params.setMargins(
        AppSizeHelper.dpToPx(left.w(context)),
        AppSizeHelper.dpToPx(top.h(context)),
        AppSizeHelper.dpToPx(right.w(context)),
        AppSizeHelper.dpToPx(bottom.h(context))
    )
    layoutParams = params
}

/**
 * Set text size using scaled font size
 * @param sizeSp Font size in sp (will be scaled)
 */
fun TextView.setScaledTextSize(sizeSp: Float) {
    textSize = sizeSp.sp(context)
}

/**
 * Set text size using moderate scale (better for tablets)
 * @param sizeSp Font size in sp
 * @param factor Scale factor (0.0-1.0)
 */
fun TextView.setModerateTextSize(sizeSp: Float, factor: Float = 0.5f) {
    textSize = sizeSp.ms(context, factor)
}