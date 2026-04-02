package net.lax1dude.eaglercraft.v1_8.internal.teavm;

import org.teavm.jso.JSBody;

import net.lax1dude.eaglercraft.v1_8.sp.server.internal.teavm.WorkerMain;

/**
 * Copyright (c) 2022-2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class MainClass {

	public static void main(String[] args) {
		installGsonAnonymousClassCompat();
		if(args.length == 1 && "_worker_process_".equalsIgnoreCase(args[0])) {
			workerMain();
		}else {
			clientMain();
		}
	}

	@JSBody(params = {}, script = "// Eagler MainClass gson compat patch\n"
			+ "if (typeof cggi_Excluder_isAnonymousOrLocal === 'function'"
			+ "        && !cggi_Excluder_isAnonymousOrLocal.$eaglerMainCompat) {"
			+ "    cggi_Excluder_isAnonymousOrLocal = function($this, $clazz) {"
			+ "        if ($clazz === null || $clazz === $rt_globals.undefined) {"
			+ "            return 0;"
			+ "        }"
			+ "        try {"
			+ "            if (typeof jl_Enum !== 'undefined' && $rt_cls(jl_Enum).$isAssignableFrom($clazz)) {"
			+ "                return 0;"
			+ "            }"
			+ "        } catch (ignored) {}"
			+ "        try {"
			+ "            if (typeof $clazz.$isLocalClass === 'function' && $clazz.$isLocalClass()) {"
			+ "                return 1;"
			+ "            }"
			+ "        } catch (ignored) {}"
			+ "        try {"
			+ "            if (typeof $clazz.$getName0 === 'function') {"
			+ "                var nObj = $clazz.$getName0();"
			+ "                if (nObj !== null && nObj !== $rt_globals.undefined) {"
			+ "                    var n = $rt_ustr(nObj);"
			+ "                    var idx = n.lastIndexOf('$');"
			+ "                    if (idx >= 0 && idx + 1 < n.length) {"
			+ "                        var anon = true;"
			+ "                        for (var i = idx + 1; i < n.length; ++i) {"
			+ "                            var ch = n.charCodeAt(i);"
			+ "                            if (ch < 48 || ch > 57) {"
			+ "                                anon = false;"
			+ "                                break;"
			+ "                            }"
			+ "                        }"
			+ "                        if (anon) {"
			+ "                            return 1;"
			+ "                        }"
			+ "                    }"
			+ "                }"
			+ "            }"
			+ "        } catch (ignored) {}"
			+ "        return 0;"
			+ "    };"
			+ "    cggi_Excluder_isAnonymousOrLocal.$eaglerMainCompat = true;"
			+ "}")
	private static native void installGsonAnonymousClassCompat();

	private static void clientMain() {
		ClientMain._main();
	}

	private static void workerMain() {
		WorkerMain._main();
	}
}
