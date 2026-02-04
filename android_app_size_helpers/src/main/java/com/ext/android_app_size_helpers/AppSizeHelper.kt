package com.ext.android_app_size_helpers

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue

object AppSizeHelper {

    // Base design dimensions (customize based on your design mockup)
    private const val BASE_WIDTH = 375f  // Standard phone width in dp
    private const val BASE_HEIGHT = 812f // Standard phone height in dp

    /**
     * Get screen width in pixels
     */
    fun screenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * Get screen height in pixels
     */
    fun screenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * Calculate width based on percentage of screen width
     * @param percent 0.0f to 1.0f (e.g., 0.5f = 50%)
     */
    fun widthPercent(context: Context, percent: Float): Int {
        require(percent in 0.0f..1.0f) { "Percent must be between 0.0 and 1.0" }
        return (screenWidth(context) * percent).toInt()
    }

    /**
     * Calculate height based on percentage of screen height
     * @param percent 0.0f to 1.0f (e.g., 0.5f = 50%)
     */
    fun heightPercent(context: Context, percent: Float): Int {
        require(percent in 0.0f..1.0f) { "Percent must be between 0.0 and 1.0" }
        return (screenHeight(context) * percent).toInt()
    }

    /**
     * Convert dp to pixels
     */
    fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        ).toInt()
    }

    /**
     * Convert sp to pixels (for text size)
     */
    fun spToPx(sp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            Resources.getSystem().displayMetrics
        ).toInt()
    }

    /**
     * Convert pixels to dp
     */
    fun pxToDp(px: Int): Float {
        return px / Resources.getSystem().displayMetrics.density
    }

    /**
     * Get screen density
     */
    fun screenDensity(): Float {
        return Resources.getSystem().displayMetrics.density
    }

    /**
     * Check if device is tablet
     */
    fun isTablet(context: Context): Boolean {
        val screenLayout = context.resources.configuration.screenLayout
        val screenSize = screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    /**
     * Get smallest screen dimension
     */
    fun smallestWidth(context: Context): Int {
        return minOf(screenWidth(context), screenHeight(context))
    }

    /**
     * Get largest screen dimension
     */
    fun largestWidth(context: Context): Int {
        return maxOf(screenWidth(context), screenHeight(context))
    }

    /**
     * Check if device is in portrait mode
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    /**
     * Check if device is in landscape mode
     */
    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * Get status bar height (for notch/safe area support)
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier(
            "status_bar_height", "dimen", "android"
        )
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    /**
     * Get navigation bar height
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier(
            "navigation_bar_height", "dimen", "android"
        )
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    /**
     * Calculate responsive text size based on screen width
     * Auto-adjusts for different device sizes
     */
    fun getResponsiveTextSize(context: Context, baseSize: Float): Float {
        val screenWidthDp = pxToDp(screenWidth(context))
        return when {
            screenWidthDp < 360 -> baseSize * 0.85f  // Small phones
            screenWidthDp < 480 -> baseSize * 1.0f   // Normal phones
            screenWidthDp < 600 -> baseSize * 1.15f  // Large phones
            screenWidthDp < 720 -> baseSize * 1.3f   // Small tablets
            else -> baseSize * 1.5f                  // Large tablets
        }
    }

    /**
     * Get optimal column count for grid layouts
     */
    fun getOptimalColumns(context: Context, minItemWidthDp: Int): Int {
        val screenWidthDp = pxToDp(screenWidth(context))
        return (screenWidthDp / minItemWidthDp).toInt().coerceAtLeast(1)
    }

    /**
     * Calculate aspect ratio height from width
     * @param width Width in pixels
     * @param ratio Aspect ratio (e.g., 16/9f for 16:9)
     */
    fun calculateAspectHeight(width: Int, ratio: Float): Int {
        return (width / ratio).toInt()
    }

    /**
     * Calculate aspect ratio width from height
     * @param height Height in pixels
     * @param ratio Aspect ratio (e.g., 16/9f for 16:9)
     */
    fun calculateAspectWidth(height: Int, ratio: Float): Int {
        return (height * ratio).toInt()
    }

    /**
     * Common aspect ratios
     */
    object AspectRatio {
        const val RATIO_16_9 = 16f / 9f
        const val RATIO_4_3 = 4f / 3f
        const val RATIO_1_1 = 1f
        const val RATIO_21_9 = 21f / 9f
        const val RATIO_3_2 = 3f / 2f
    }

    /**
     * Screen size categories based on Material Design
     */
    enum class ScreenCategory {
        SMALL,      // < 360dp
        NORMAL,     // 360-480dp
        LARGE,      // 480-600dp
        XLARGE,     // 600-720dp
        XXLARGE     // > 720dp
    }

    /**
     * Get current screen category
     */
    fun getScreenCategory(context: Context): ScreenCategory {
        val widthDp = pxToDp(screenWidth(context))
        return when {
            widthDp < 360 -> ScreenCategory.SMALL
            widthDp < 480 -> ScreenCategory.NORMAL
            widthDp < 600 -> ScreenCategory.LARGE
            widthDp < 720 -> ScreenCategory.XLARGE
            else -> ScreenCategory.XXLARGE
        }
    }

    // ========== SCALING FUNCTIONS (Flutter/React Native style) ==========

    /**
     * Scale width proportionally to screen width
     * Similar to .w in Flutter ScreenUtil
     * @param size Size in dp from design
     */
    fun scaleWidth(context: Context, size: Float): Float {
        val screenWidthDp = pxToDp(screenWidth(context))
        return (size / BASE_WIDTH) * screenWidthDp
    }

    /**
     * Scale height proportionally to screen height
     * Similar to .h in Flutter ScreenUtil
     * @param size Size in dp from design
     */
    fun scaleHeight(context: Context, size: Float): Float {
        val screenHeightDp = pxToDp(screenHeight(context))
        return (size / BASE_HEIGHT) * screenHeightDp
    }

    /**
     * Linear scale based on screen width
     * Similar to scale() in react-native-size-matters
     * @param size Base size in dp
     */
    fun scale(context: Context, size: Float): Float {
        val screenWidthDp = pxToDp(screenWidth(context))
        val scaleFactor = screenWidthDp / BASE_WIDTH
        return size * scaleFactor
    }

    /**
     * Linear scale based on screen height
     * Similar to verticalScale() in react-native-size-matters
     * @param size Base size in dp
     */
    fun verticalScale(context: Context, size: Float): Float {
        val screenHeightDp = pxToDp(screenHeight(context))
        val scaleFactor = screenHeightDp / BASE_HEIGHT
        return size * scaleFactor
    }

    /**
     * Moderate scale with custom factor
     * Prevents elements from becoming too large on tablets
     * Similar to moderateScale() in react-native-size-matters
     *
     * @param size Base size in dp
     * @param factor Resize factor (0.0 to 1.0). Default 0.5
     *               0.0 = no scaling, 1.0 = full scaling
     */
    fun moderateScale(context: Context, size: Float, factor: Float = 0.5f): Float {
        val scaled = scale(context, size)
        return size + (scaled - size) * factor
    }

    /**
     * Scale font size proportionally
     * Similar to .sp in Flutter ScreenUtil
     * @param size Font size in sp from design
     */
    fun scaleFontSize(context: Context, size: Float): Float {
        return scaleWidth(context, size)
    }

    /**
     * Scale border radius proportionally
     * Similar to .r in Flutter ScreenUtil
     * @param size Radius in dp from design
     */
    fun scaleRadius(context: Context, size: Float): Float {
        return scaleWidth(context, size)
    }

    /**
     * Set custom base design dimensions
     * Call this if your design is based on different dimensions
     * For example: Figma designs at 414x896
     *
     * Note: To use this, make BASE_WIDTH and BASE_HEIGHT mutable vars
     */
    fun setBaseDesignSize(width: Float, height: Float) {
        // Note: This requires BASE_WIDTH/HEIGHT to be mutable
        // For now, modify the constants above directly
    }
}