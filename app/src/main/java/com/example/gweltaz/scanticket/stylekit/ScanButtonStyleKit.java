package com.example.gweltaz.scanticket.stylekit;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

import java.util.Stack;


/**
 * Created by AuthorName on 16 oct. 2017.
 * Copyright © 2017 CompanyName. All rights reserved.
 *
 * Generated by PaintCode
 * http://www.paintcodeapp.com
 *
 * @author AuthorName
 */
public class ScanButtonStyleKit {


    // Resizing Behavior
    public enum ResizingBehavior {
        AspectFit, //!< The content is proportionally resized to fit into the target rectangle.
        AspectFill, //!< The content is proportionally resized to completely fill the target rectangle.
        Stretch, //!< The content is stretched to match the entire target rectangle.
        Center, //!< The content is centered in the target rectangle, but it is NOT resized.
    }

    // Canvas Drawings
    // Tab

    private static class CacheForCanvas1 {
        private static RectF originalFrame = new RectF(0f, 0f, 240f, 120f);
        private static RectF resizedFrame = new RectF();
    }

    public static void drawCanvas1(Canvas canvas) {
        ScanButtonStyleKit.drawCanvas1(canvas, new RectF(0f, 0f, 240f, 120f), ResizingBehavior.AspectFit);
    }

    public static void drawCanvas1(Canvas canvas, RectF targetFrame, ResizingBehavior resizing) {
        // Resize to Target Frame
        canvas.save();
        RectF resizedFrame = CacheForCanvas1.resizedFrame;
        ScanButtonStyleKit.resizingBehaviorApply(resizing, CacheForCanvas1.originalFrame, targetFrame, resizedFrame);
        canvas.translate(resizedFrame.left, resizedFrame.top);
        canvas.scale(resizedFrame.width() / 240f, resizedFrame.height() / 120f);

        // Empty.

        canvas.restore();
    }

    private static class CacheForButton {
        private static Paint paint = new Paint();
        private static RectF originalFrame = new RectF(0f, 0f, 39f, 39f);
        private static RectF resizedFrame = new RectF();
        private static RectF insideRect = new RectF();
        private static Path insidePath = new Path();
        private static RectF outsideRect = new RectF();
        private static Path outsidePath = new Path();
    }

    public static void drawButton(Canvas canvas, float arcRotation, float centerScale) {
        ScanButtonStyleKit.drawButton(canvas, new RectF(0f, 0f, 39f, 39f), ResizingBehavior.AspectFit, arcRotation, centerScale);
    }

    public static void drawButton(Canvas canvas, RectF targetFrame, ResizingBehavior resizing, float arcRotation, float centerScale) {
        // General Declarations
        Stack<Matrix> currentTransformation = new Stack<Matrix>();
        currentTransformation.push(new Matrix());
        Paint paint = CacheForButton.paint;

        // Local Colors
        int fillColor4 = Color.argb(255, 255, 255, 255);
        int strokeColor = Color.argb(255, 139, 195, 74);

        // Resize to Target Frame
        canvas.save();
        RectF resizedFrame = CacheForButton.resizedFrame;
        ScanButtonStyleKit.resizingBehaviorApply(resizing, CacheForButton.originalFrame, targetFrame, resizedFrame);
        canvas.translate(resizedFrame.left, resizedFrame.top);
        canvas.scale(resizedFrame.width() / 39f, resizedFrame.height() / 39f);

        // inside
        canvas.save();
        canvas.translate(19.5f, 19.5f);
        currentTransformation.peek().postTranslate(19.5f, 19.5f);
        canvas.scale(centerScale, centerScale);
        currentTransformation.peek().postScale(centerScale, centerScale);
        RectF insideRect = CacheForButton.insideRect;
        insideRect.set(-6f, -6f, 6f, 6f);
        Path insidePath = CacheForButton.insidePath;
        insidePath.reset();
        insidePath.addOval(insideRect, Path.Direction.CW);

        paint.reset();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor4);
        canvas.drawPath(insidePath, paint);
        canvas.restore();

        // outside
        canvas.save();
        canvas.translate(19.5f, 19.5f);
        currentTransformation.peek().postTranslate(19.5f, 19.5f);
        canvas.rotate(-arcRotation);
        currentTransformation.peek().postRotate(-arcRotation);
        RectF outsideRect = CacheForButton.outsideRect;
        outsideRect.set(-16.7f, -16.7f, 16.7f, 16.7f);
        Path outsidePath = CacheForButton.outsidePath;
        outsidePath.reset();
        outsidePath.addArc(outsideRect, -378f, 275f);

        paint.reset();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1f);
        paint.setStrokeMiter(10f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeColor);
        canvas.drawPath(outsidePath, paint);
        canvas.restore();
        canvas.restore();

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