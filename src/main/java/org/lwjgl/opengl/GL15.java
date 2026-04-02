/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import net.lax1dude.eaglercraft.v1_8.internal.IBufferGL;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;

import static net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL.*;

public class GL15 extends GL14 {

	public static final int GL_ARRAY_BUFFER = 0x8892;
	public static final int GL_ELEMENT_ARRAY_BUFFER = 0x8893;
	public static final int GL_ARRAY_BUFFER_BINDING = 0x8894;
	public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;
	public static final int GL_STREAM_DRAW = 0x88E0;
	public static final int GL_STREAM_READ = 0x88E1;
	public static final int GL_STREAM_COPY = 0x88E2;
	public static final int GL_STATIC_DRAW = 0x88E4;
	public static final int GL_STATIC_READ = 0x88E5;
	public static final int GL_STATIC_COPY = 0x88E6;
	public static final int GL_DYNAMIC_DRAW = 0x88E8;
	public static final int GL_DYNAMIC_READ = 0x88E9;
	public static final int GL_DYNAMIC_COPY = 0x88EA;
	public static final int GL_READ_ONLY = 0x88B8;
	public static final int GL_WRITE_ONLY = 0x88B9;
	public static final int GL_READ_WRITE = 0x88BA;
	public static final int GL_BUFFER_SIZE = 0x8764;
	public static final int GL_BUFFER_USAGE = 0x8765;
	public static final int GL_BUFFER_ACCESS = 0x88BB;
	public static final int GL_BUFFER_MAPPED = 0x88BC;
	public static final int GL_BUFFER_MAP_POINTER = 0x88BD;
	public static final int GL_QUERY_COUNTER_BITS = 0x8864;
	public static final int GL_CURRENT_QUERY = 0x8865;
	public static final int GL_QUERY_RESULT = 0x8866;
	public static final int GL_QUERY_RESULT_AVAILABLE = 0x8867;
	public static final int GL_SAMPLES_PASSED = 0x8914;
	public static final int GL_SRC1_ALPHA = 0x8589;

	private static final AtomicInteger NEXT_BUFFER_ID = new AtomicInteger(1);
	private static final Map<Integer, IBufferGL> BUFFERS = new HashMap<>();

	private static IBufferGL getBuffer(int id) {
		return id == 0 ? null : BUFFERS.get(id);
	}

	private static net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer copyBuffer(ByteBuffer src) {
		if (src == null) {
			return null;
		}
		int remaining = src.remaining();
		net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer dst = PlatformRuntime.allocateByteBuffer(remaining);
		int pos = src.position();
		for (int i = 0; i < remaining; ++i) {
			dst.put(i, src.get(pos + i));
		}
		dst.position(0).limit(remaining);
		return dst;
	}

	private static net.lax1dude.eaglercraft.v1_8.internal.buffer.IntBuffer copyBuffer(IntBuffer src) {
		if (src == null) {
			return null;
		}
		int remaining = src.remaining();
		net.lax1dude.eaglercraft.v1_8.internal.buffer.IntBuffer dst = PlatformRuntime.allocateIntBuffer(remaining);
		int pos = src.position();
		for (int i = 0; i < remaining; ++i) {
			dst.put(i, src.get(pos + i));
		}
		dst.position(0).limit(remaining);
		return dst;
	}

	private static net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer copyBuffer(FloatBuffer src) {
		if (src == null) {
			return null;
		}
		int remaining = src.remaining();
		net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer dst = PlatformRuntime.allocateFloatBuffer(remaining);
		int pos = src.position();
		for (int i = 0; i < remaining; ++i) {
			dst.put(i, src.get(pos + i));
		}
		dst.position(0).limit(remaining);
		return dst;
	}

	public static int glGenBuffers() {
		int id = NEXT_BUFFER_ID.getAndIncrement();
		BUFFERS.put(id, _wglGenBuffers());
		return id;
	}

	public static void glGenBuffers(IntBuffer buffers) {
		if (buffers == null) {
			return;
		}
		for (int i = buffers.position(); i < buffers.limit(); ++i) {
			buffers.put(i, glGenBuffers());
		}
	}

	public static void glBindBuffer(int target, int buffer) {
		_wglBindBuffer(target, getBuffer(buffer));
	}

	public static void glBufferData(int target, ByteBuffer data, int usage) {
		net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer copy = copyBuffer(data);
		try {
			_wglBufferData(target, copy, usage);
		} finally {
			if (copy != null) {
				PlatformRuntime.freeByteBuffer(copy);
			}
		}
	}

	public static void glBufferData(int target, IntBuffer data, int usage) {
		net.lax1dude.eaglercraft.v1_8.internal.buffer.IntBuffer copy = copyBuffer(data);
		try {
			_wglBufferData(target, copy, usage);
		} finally {
			if (copy != null) {
				PlatformRuntime.freeIntBuffer(copy);
			}
		}
	}

	public static void glBufferData(int target, FloatBuffer data, int usage) {
		net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer copy = copyBuffer(data);
		try {
			_wglBufferData(target, copy, usage);
		} finally {
			if (copy != null) {
				PlatformRuntime.freeFloatBuffer(copy);
			}
		}
	}

	public static void glBufferData(int target, int size, int usage) {
		_wglBufferData(target, size, usage);
	}

	public static void glBufferData(int target, long size, int usage) {
		_wglBufferData(target, (int) size, usage);
	}

	public static void glDeleteBuffers(int buffer) {
		IBufferGL obj = BUFFERS.remove(buffer);
		if (obj != null) {
			_wglDeleteBuffers(obj);
		}
	}

	public static void glDeleteBuffers(IntBuffer buffers) {
		if (buffers == null) {
			return;
		}
		for (int i = buffers.position(); i < buffers.limit(); ++i) {
			glDeleteBuffers(buffers.get(i));
		}
	}
}
