package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2025 lax1dude, ayunami2000. All Rights Reserved.
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
public class GuiBetterFpsSettings extends GuiScreen {
	private final GuiScreen parentScreen;
	private final GameSettings gameSettings;
	private final String startAlgorithm;
	private GuiButton algorithmButton;
	private String title;

	public GuiBetterFpsSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
		this.parentScreen = parentScreenIn;
		this.gameSettings = gameSettingsIn;
		this.startAlgorithm = gameSettingsIn.betterFpsAlgorithm;
	}

	public void initGui() {
		this.buttonList.clear();
		this.title = I18n.format("options.betterFps.title", new Object[0]);
		this.buttonList.add(this.algorithmButton = new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24, 200,
				20, this.getAlgorithmLabel()));
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 144,
				I18n.format("gui.done", new Object[0])));
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 0) {
				this.gameSettings.cycleBetterFpsAlgorithm();
				this.algorithmButton.displayString = this.getAlgorithmLabel();
			}

			if (parGuiButton.id == 200) {
				this.mc.displayGuiScreen(this.parentScreen);
			}
		}
	}

	public void onGuiClosed() {
		if (!this.startAlgorithm.equals(this.gameSettings.betterFpsAlgorithm)) {
			this.gameSettings.saveOptions();
		}
	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
		this.drawCenteredString(this.fontRendererObj, I18n.format("options.betterFps.description.0", new Object[0]),
				this.width / 2, this.height / 6 + 64, 10526880);
		this.drawCenteredString(this.fontRendererObj, I18n.format("options.betterFps.description.1", new Object[0]),
				this.width / 2, this.height / 6 + 76, 10526880);
		this.drawCenteredString(this.fontRendererObj, I18n.format("options.betterFps.description.2", new Object[0]),
				this.width / 2, this.height / 6 + 100, 8421504);
		super.drawScreen(i, j, f);
	}

	private String getAlgorithmLabel() {
		return I18n.format("options.betterFps.algorithm", new Object[0]) + ": "
				+ I18n.format("options.betterFps.algorithm." + this.gameSettings.betterFpsAlgorithm, new Object[0]);
	}
}