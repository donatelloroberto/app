package com.tokbox.cordova;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.opentok.android.BaseVideoRenderer;
import java.io.ByteArrayOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class OpenTokCustomVideoRenderer extends BaseVideoRenderer {
    /* access modifiers changed from: private */
    public static final String LOG_TAG = OpenTokCustomVideoRenderer.class.getSimpleName();
    private Context mContext;
    private MyRenderer mRenderer = new MyRenderer();
    private GLSurfaceView mView;

    static class MyRenderer implements GLSurfaceView.Renderer {
        static final int COORDS_PER_VERTEX = 3;
        static final int TEXTURECOORDS_PER_VERTEX = 2;
        static float[] mUVCoords = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
        static float[] mXYZCoords = {-1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f};
        private final String fragmentShaderCode = "precision mediump float;\nuniform sampler2D Ytex;\nuniform sampler2D Utex,Vtex;\nvarying vec2 vTextureCoord;\nvoid main(void) {\n  float nx,ny,r,g,b,y,u,v;\n  mediump vec4 txl,ux,vx;  nx=vTextureCoord[0];\n  ny=vTextureCoord[1];\n  y=texture2D(Ytex,vec2(nx,ny)).r;\n  u=texture2D(Utex,vec2(nx,ny)).r;\n  v=texture2D(Vtex,vec2(nx,ny)).r;\n  y=1.1643*(y-0.0625);\n  u=u-0.5;\n  v=v-0.5;\n  r=y+1.5958*v;\n  g=y-0.39173*u-0.81290*v;\n  b=y+2.017*u;\n  gl_FragColor=vec4(r,g,b,1.0);\n}\n";
        private CallbackContext mCallbackContext;
        BaseVideoRenderer.Frame mCurrentFrame;
        private ShortBuffer mDrawListBuffer;
        ReentrantLock mFrameLock = new ReentrantLock();
        private int mProgram;
        private Boolean mSaveScreenshot = false;
        float[] mScaleMatrix = new float[16];
        private FloatBuffer mTextureBuffer;
        private int mTextureHeight;
        int[] mTextureIds = new int[3];
        private int mTextureWidth;
        private FloatBuffer mVertexBuffer;
        private short[] mVertexIndex = {0, 1, 2, 0, 2, 3};
        boolean mVideoDisabled = false;
        boolean mVideoFitEnabled = true;
        private int mViewportHeight;
        private int mViewportWidth;
        private final String vertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 aPosition;\nattribute vec2 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = aTextureCoord;\n}\n";

        public MyRenderer() {
            ByteBuffer bb = ByteBuffer.allocateDirect(mXYZCoords.length * 4);
            bb.order(ByteOrder.nativeOrder());
            this.mVertexBuffer = bb.asFloatBuffer();
            this.mVertexBuffer.put(mXYZCoords);
            this.mVertexBuffer.position(0);
            ByteBuffer tb = ByteBuffer.allocateDirect(mUVCoords.length * 4);
            tb.order(ByteOrder.nativeOrder());
            this.mTextureBuffer = tb.asFloatBuffer();
            this.mTextureBuffer.put(mUVCoords);
            this.mTextureBuffer.position(0);
            ByteBuffer dlb = ByteBuffer.allocateDirect(this.mVertexIndex.length * 2);
            dlb.order(ByteOrder.nativeOrder());
            this.mDrawListBuffer = dlb.asShortBuffer();
            this.mDrawListBuffer.put(this.mVertexIndex);
            this.mDrawListBuffer.position(0);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GLES20.glClear(16384);
            int vertexShader = loadShader(35633, "uniform mat4 uMVPMatrix;attribute vec4 aPosition;\nattribute vec2 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = aTextureCoord;\n}\n");
            int fragmentShader = loadShader(35632, "precision mediump float;\nuniform sampler2D Ytex;\nuniform sampler2D Utex,Vtex;\nvarying vec2 vTextureCoord;\nvoid main(void) {\n  float nx,ny,r,g,b,y,u,v;\n  mediump vec4 txl,ux,vx;  nx=vTextureCoord[0];\n  ny=vTextureCoord[1];\n  y=texture2D(Ytex,vec2(nx,ny)).r;\n  u=texture2D(Utex,vec2(nx,ny)).r;\n  v=texture2D(Vtex,vec2(nx,ny)).r;\n  y=1.1643*(y-0.0625);\n  u=u-0.5;\n  v=v-0.5;\n  r=y+1.5958*v;\n  g=y-0.39173*u-0.81290*v;\n  b=y+2.017*u;\n  gl_FragColor=vec4(r,g,b,1.0);\n}\n");
            this.mProgram = GLES20.glCreateProgram();
            GLES20.glAttachShader(this.mProgram, vertexShader);
            GLES20.glAttachShader(this.mProgram, fragmentShader);
            GLES20.glLinkProgram(this.mProgram);
            int positionHandle = GLES20.glGetAttribLocation(this.mProgram, "aPosition");
            int textureHandle = GLES20.glGetAttribLocation(this.mProgram, "aTextureCoord");
            GLES20.glVertexAttribPointer(positionHandle, 3, 5126, false, 12, this.mVertexBuffer);
            GLES20.glEnableVertexAttribArray(positionHandle);
            GLES20.glVertexAttribPointer(textureHandle, 2, 5126, false, 8, this.mTextureBuffer);
            GLES20.glEnableVertexAttribArray(textureHandle);
            GLES20.glUseProgram(this.mProgram);
            GLES20.glUniform1i(GLES20.glGetUniformLocation(this.mProgram, "Ytex"), 0);
            GLES20.glUniform1i(GLES20.glGetUniformLocation(this.mProgram, "Utex"), 1);
            GLES20.glUniform1i(GLES20.glGetUniformLocation(this.mProgram, "Vtex"), 2);
            this.mTextureWidth = 0;
            this.mTextureHeight = 0;
        }

        static void initializeTexture(int name, int id, int width, int height) {
            GLES20.glActiveTexture(name);
            GLES20.glBindTexture(3553, id);
            GLES20.glTexParameterf(3553, 10241, 9728.0f);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6409, width, height, 0, 6409, 5121, (Buffer) null);
        }

        /* access modifiers changed from: package-private */
        public void setupTextures(BaseVideoRenderer.Frame frame) {
            if (this.mTextureIds[0] != 0) {
                GLES20.glDeleteTextures(3, this.mTextureIds, 0);
            }
            GLES20.glGenTextures(3, this.mTextureIds, 0);
            int w = frame.getWidth();
            int h = frame.getHeight();
            int hw = (w + 1) >> 1;
            int hh = (h + 1) >> 1;
            initializeTexture(33984, this.mTextureIds[0], w, h);
            initializeTexture(33985, this.mTextureIds[1], hw, hh);
            initializeTexture(33986, this.mTextureIds[2], hw, hh);
            this.mTextureWidth = frame.getWidth();
            this.mTextureHeight = frame.getHeight();
        }

        /* access modifiers changed from: package-private */
        public void updateTextures(BaseVideoRenderer.Frame frame) {
            int width = frame.getWidth();
            int height = frame.getHeight();
            int half_width = (width + 1) >> 1;
            int half_height = (height + 1) >> 1;
            int y_size = width * height;
            int uv_size = half_width * half_height;
            ByteBuffer bb = frame.getBuffer();
            bb.clear();
            if (bb.remaining() == (uv_size * 2) + y_size) {
                bb.position(0);
                GLES20.glPixelStorei(3317, 1);
                GLES20.glPixelStorei(3333, 1);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.mTextureIds[0]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, width, height, 6409, 5121, bb);
                bb.position(y_size);
                GLES20.glActiveTexture(33985);
                GLES20.glBindTexture(3553, this.mTextureIds[1]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, half_width, half_height, 6409, 5121, bb);
                bb.position(y_size + uv_size);
                GLES20.glActiveTexture(33986);
                GLES20.glBindTexture(3553, this.mTextureIds[2]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, half_width, half_height, 6409, 5121, bb);
                return;
            }
            this.mTextureWidth = 0;
            this.mTextureHeight = 0;
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            this.mViewportWidth = width;
            this.mViewportHeight = height;
        }

        public void onDrawFrame(GL10 gl) {
            float f;
            this.mFrameLock.lock();
            if (this.mCurrentFrame == null || this.mVideoDisabled) {
                gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                GLES20.glClear(16384);
            } else {
                GLES20.glUseProgram(this.mProgram);
                if (!(this.mTextureWidth == this.mCurrentFrame.getWidth() && this.mTextureHeight == this.mCurrentFrame.getHeight())) {
                    setupTextures(this.mCurrentFrame);
                }
                updateTextures(this.mCurrentFrame);
                Matrix.setIdentityM(this.mScaleMatrix, 0);
                float scaleX = 1.0f;
                float scaleY = 1.0f;
                float ratio = ((float) this.mCurrentFrame.getWidth()) / ((float) this.mCurrentFrame.getHeight());
                float vratio = ((float) this.mViewportWidth) / ((float) this.mViewportHeight);
                if (this.mVideoFitEnabled) {
                    if (ratio > vratio) {
                        scaleY = vratio / ratio;
                    } else {
                        scaleX = ratio / vratio;
                    }
                } else if (ratio < vratio) {
                    scaleY = vratio / ratio;
                } else {
                    scaleX = ratio / vratio;
                }
                float[] fArr = this.mScaleMatrix;
                if (this.mCurrentFrame.isMirroredX()) {
                    f = -1.0f;
                } else {
                    f = 1.0f;
                }
                Matrix.scaleM(fArr, 0, f * scaleX, scaleY, 1.0f);
                GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix"), 1, false, this.mScaleMatrix, 0);
                GLES20.glDrawElements(4, this.mVertexIndex.length, 5123, this.mDrawListBuffer);
            }
            this.mFrameLock.unlock();
        }

        public void getSnapshot(CallbackContext callbackContext) {
            this.mCallbackContext = callbackContext;
            this.mSaveScreenshot = true;
        }

        public void displayFrame(BaseVideoRenderer.Frame frame) {
            this.mFrameLock.lock();
            if (this.mCurrentFrame != null) {
                this.mCurrentFrame.recycle();
            }
            this.mCurrentFrame = frame;
            this.mFrameLock.unlock();
            if (this.mSaveScreenshot.booleanValue()) {
                Log.d(OpenTokCustomVideoRenderer.LOG_TAG, "Capturing frame....");
                ByteBuffer bb = frame.getBuffer();
                bb.clear();
                int width = frame.getWidth();
                int height = frame.getHeight();
                byte[] yuv = new byte[((((width + 1) >> 1) * ((height + 1) >> 1) * 2) + (width * height))];
                bb.get(yuv);
                int[] intArray = new int[(width * height)];
                decodeYUV420(intArray, yuv, width, height);
                Bitmap bmp = Bitmap.createBitmap(intArray, width, height, Bitmap.Config.ARGB_8888);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    this.mCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, "data:image/png;base64," + Base64.encodeToString(baos.toByteArray(), 0)));
                    this.mSaveScreenshot = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void decodeYUV420(int[] rgba, byte[] yuv420, int width, int height) {
            int half_width = (width + 1) >> 1;
            int y_size = width * height;
            int uv_size = half_width * ((height + 1) >> 1);
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    double y = (double) (yuv420[(j * width) + i] & 255);
                    double v = (double) (yuv420[((j >> 1) * half_width) + y_size + (i >> 1)] & 255);
                    double u = (double) (yuv420[y_size + uv_size + ((j >> 1) * half_width) + (i >> 1)] & 255);
                    double r = y + (1.402d * (u - 128.0d));
                    double g = (y - (0.34414d * (v - 128.0d))) - (0.71414d * (u - 128.0d));
                    double b = y + (1.772d * (v - 128.0d));
                    if (r < 0.0d) {
                        r = 0.0d;
                    } else if (r > 255.0d) {
                        r = 255.0d;
                    }
                    if (g < 0.0d) {
                        g = 0.0d;
                    } else if (g > 255.0d) {
                        g = 255.0d;
                    }
                    if (b < 0.0d) {
                        b = 0.0d;
                    } else if (b > 255.0d) {
                        b = 255.0d;
                    }
                    rgba[(j * width) + i] = -16777216 | (((int) r) << 16) | (((int) g) << 8) | ((int) b);
                }
            }
        }

        public static int loadShader(int type, String shaderCode) {
            int shader = GLES20.glCreateShader(type);
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }

        public void disableVideo(boolean b) {
            this.mFrameLock.lock();
            this.mVideoDisabled = b;
            if (this.mVideoDisabled) {
                if (this.mCurrentFrame != null) {
                    this.mCurrentFrame.recycle();
                }
                this.mCurrentFrame = null;
            }
            this.mFrameLock.unlock();
        }

        public void enableVideoFit(boolean enableVideoFit) {
            this.mVideoFitEnabled = enableVideoFit;
        }
    }

    public OpenTokCustomVideoRenderer(Context context) {
        this.mContext = context;
        this.mView = new GLSurfaceView(context);
        this.mView.setEGLContextClientVersion(2);
        this.mView.setZOrderMediaOverlay(true);
        this.mView.setRenderer(this.mRenderer);
        this.mView.setRenderMode(0);
    }

    public void onFrame(BaseVideoRenderer.Frame frame) {
        this.mRenderer.displayFrame(frame);
        this.mView.requestRender();
    }

    public void getSnapshot(CallbackContext callbackContext) {
        this.mRenderer.getSnapshot(callbackContext);
    }

    public void setStyle(String key, String value) {
        if (!BaseVideoRenderer.STYLE_VIDEO_SCALE.equals(key)) {
            return;
        }
        if (BaseVideoRenderer.STYLE_VIDEO_FIT.equals(value)) {
            this.mRenderer.enableVideoFit(true);
        } else if (BaseVideoRenderer.STYLE_VIDEO_FILL.equals(value)) {
            this.mRenderer.enableVideoFit(false);
        }
    }

    public void onVideoPropertiesChanged(boolean videoEnabled) {
        Log.i(LOG_TAG, "onVideoPropertiesChanged " + Boolean.toString(videoEnabled));
        this.mRenderer.disableVideo(!videoEnabled);
    }

    public View getView() {
        return this.mView;
    }

    public void onPause() {
        this.mView.onPause();
    }

    public void onResume() {
        this.mView.onResume();
    }
}
