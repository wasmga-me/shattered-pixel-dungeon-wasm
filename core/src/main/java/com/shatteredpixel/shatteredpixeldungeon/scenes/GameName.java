/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2026 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

//This version is renamed, as Shattered Pixel Dungeon's author asks of other versions: the title and
// welcome screens show the name as text in the game's pixel font in place of the banner artwork.
// The credits on the About screen are unchanged.
public class GameName {

	public static final String NAME = "Ember Pixel Dungeon";

	//adds the name, centered where the (hidden) banner would be
	public static RenderedTextBlock add( PixelScene scene, Image banner, boolean landscape ){
		RenderedTextBlock name = PixelScene.renderTextBlock(landscape ? "EMBER PIXEL DUNGEON" : "EMBER\nPIXEL DUNGEON", 16);
		name.maxWidth((int)Math.max(banner.width(), 120));
		name.align(RenderedTextBlock.CENTER_ALIGN);
		name.hardlight(Window.TITLE_COLOR);
		name.setPos(banner.x + (banner.width() - name.width()) / 2f, banner.y + (banner.height() - name.height()) / 2f);
		PixelScene.align(name);
		scene.add(name);
		return name;
	}
}
