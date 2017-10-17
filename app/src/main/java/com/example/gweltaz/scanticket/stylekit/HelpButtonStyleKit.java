package com.example.gweltaz.scanticket.stylekit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by gweltaz on 17/10/2017.
 */

public class HelpButtonStyleKit {


    // Resizing Behavior
    public enum ResizingBehavior {
        AspectFit, //!< The content is proportionally resized to fit into the target rectangle.
        AspectFill, //!< The content is proportionally resized to completely fill the target rectangle.
        Stretch, //!< The content is stretched to match the entire target rectangle.
        Center, //!< The content is centered in the target rectangle, but it is NOT resized.
    }

    // Canvas Drawings
    // Tab

    private static class CacheForHelpButton {
        private static Paint paint = new Paint();
        private static RectF originalFrame = new RectF(0f, 0f, 30f, 30f);
        private static RectF resizedFrame = new RectF();
        private static RectF questionMarkRect = new RectF();
        private static Path questionMarkPath = new Path();
    }

    public static void drawHelpButton(Canvas canvas,float opacity) {
        HelpButtonStyleKit.drawHelpButton(canvas, new RectF(0f, 0f, 30f, 30f), ResizingBehavior.AspectFit,opacity);
    }

    public static void drawHelpButton(Canvas canvas, RectF targetFrame, ResizingBehavior resizing,float opacity) {
        // General Declarations
        Paint paint = CacheForHelpButton.paint;

        // Local Colors
        int fillColor2 = Color.argb((int)((opacity / 100) * 255.0f), 255, 255, 255);

        // Resize to Target Frame
        canvas.save();
        RectF resizedFrame = CacheForHelpButton.resizedFrame;
        HelpButtonStyleKit.resizingBehaviorApply(resizing, CacheForHelpButton.originalFrame, targetFrame, resizedFrame);
        canvas.translate(resizedFrame.left, resizedFrame.top);
        canvas.scale(resizedFrame.width() / 30f, resizedFrame.height() / 30f);

        // questionMark
        RectF questionMarkRect = CacheForHelpButton.questionMarkRect;
        questionMarkRect.set(0f, 0f, 30f, 30f);
        Path questionMarkPath = CacheForHelpButton.questionMarkPath;
        questionMarkPath.reset();
        questionMarkPath.moveTo(15f, 0f);
        questionMarkPath.cubicTo(6.72f, 0f, 0f, 6.72f, 0f, 15f);
        questionMarkPath.cubicTo(0f, 23.28f, 6.72f, 30f, 15f, 30f);
        questionMarkPath.cubicTo(23.28f, 30f, 30f, 23.28f, 30f, 15f);
        questionMarkPath.cubicTo(30f, 6.72f, 23.28f, 0f, 15f, 0f);
        questionMarkPath.close();
        questionMarkPath.moveTo(15f, 1.2f);
        questionMarkPath.cubicTo(22.63f, 1.2f, 28.8f, 7.37f, 28.8f, 15f);
        questionMarkPath.cubicTo(28.8f, 22.63f, 22.63f, 28.8f, 15f, 28.8f);
        questionMarkPath.cubicTo(7.37f, 28.8f, 1.2f, 22.63f, 1.2f, 15f);
        questionMarkPath.cubicTo(1.2f, 7.37f, 7.37f, 1.2f, 15f, 1.2f);
        questionMarkPath.close();
        questionMarkPath.moveTo(15.17f, 7.5f);
        questionMarkPath.cubicTo(12.93f, 7.5f, 11.44f, 8.87f, 11.12f, 10.93f);
        questionMarkPath.cubicTo(11.1f, 11.06f, 11.18f, 11.13f, 11.31f, 11.16f);
        questionMarkPath.lineTo(12.66f, 11.4f);
        questionMarkPath.cubicTo(12.79f, 11.42f, 12.88f, 11.36f, 12.9f, 11.23f);
        questionMarkPath.cubicTo(13.16f, 9.92f, 13.93f, 9.19f, 15.13f, 9.19f);
        questionMarkPath.cubicTo(16.36f, 9.19f, 17.21f, 9.97f, 17.21f, 11.19f);
        questionMarkPath.cubicTo(17.21f, 11.93f, 16.95f, 12.41f, 16.2f, 13.44f);
        questionMarkPath.lineTo(14.76f, 15.43f);
        questionMarkPath.cubicTo(14.3f, 16.06f, 14.12f, 16.5f, 14.12f, 17.36f);
        questionMarkPath.lineTo(14.12f, 18.24f);
        questionMarkPath.cubicTo(14.12f, 18.37f, 14.21f, 18.45f, 14.34f, 18.45f);
        questionMarkPath.lineTo(15.75f, 18.45f);
        questionMarkPath.cubicTo(15.88f, 18.45f, 15.98f, 18.37f, 15.98f, 18.24f);
        questionMarkPath.lineTo(15.98f, 17.55f);
        questionMarkPath.cubicTo(15.98f, 16.82f, 16.11f, 16.52f, 16.54f, 15.94f);
        questionMarkPath.lineTo(17.96f, 13.97f);
        questionMarkPath.cubicTo(18.69f, 12.96f, 19.07f, 12.19f, 19.07f, 11.16f);
        questionMarkPath.cubicTo(19.07f, 9.03f, 17.49f, 7.5f, 15.17f, 7.5f);
        questionMarkPath.close();
        questionMarkPath.moveTo(14.23f, 20.1f);
        questionMarkPath.cubicTo(14.1f, 20.1f, 14.01f, 20.18f, 14.01f, 20.31f);
        questionMarkPath.lineTo(14.01f, 22.16f);
        questionMarkPath.cubicTo(14.01f, 22.29f, 14.1f, 22.37f, 14.23f, 22.37f);
        questionMarkPath.lineTo(15.86f, 22.37f);
        questionMarkPath.cubicTo(15.99f, 22.37f, 16.09f, 22.29f, 16.09f, 22.16f);
        questionMarkPath.lineTo(16.09f, 20.31f);
        questionMarkPath.cubicTo(16.09f, 20.18f, 15.99f, 20.1f, 15.86f, 20.1f);
        questionMarkPath.lineTo(14.23f, 20.1f);
        questionMarkPath.close();

        paint.reset();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor2);
        canvas.drawPath(questionMarkPath, paint);

        canvas.restore();
    }


    // Resizing Behavior
    public static void resizingBehaviorApply(ResizingBehavior behavior, RectF rect, RectF target, RectF result) {
        if (rect.equals(target) || target == null) {
            result.set(rect);
            return;
        }

        if (behavior == ResizingBehavior.Stretch) {
            result.set(target);
            return;
        }

        float xRatio = Math.abs(target.width() / rect.width());
        float yRatio = Math.abs(target.height() / rect.height());
        float scale = 0f;

        switch (behavior) {
            case AspectFit: {
                scale = Math.min(xRatio, yRatio);
                break;
            }
            case AspectFill: {
                scale = Math.max(xRatio, yRatio);
                break;
            }
            case Center: {
                scale = 1f;
                break;
            }
        }

        float newWidth = Math.abs(rect.width() * scale);
        float newHeight = Math.abs(rect.height() * scale);
        result.set(target.centerX() - newWidth / 2,
                target.centerY() - newHeight / 2,
                target.centerX() + newWidth / 2,
                target.centerY() + newHeight / 2);
    }


}