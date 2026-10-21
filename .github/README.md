# Ember Pixel Dungeon

This fork is the web build of [Shattered Pixel Dungeon](https://github.com/00-Evan/shattered-pixel-dungeon) that runs on wasmga.me. Play it at **https://wasmga.me/games/ember-pixel-dungeon**.

Shattered Pixel Dungeon's author asks other versions of the game to use a name of their own, so this one is called Ember Pixel Dungeon. The game is Shattered Pixel Dungeon v4.0.2 by Evan Debenham, based on Pixel Dungeon by Oleg Dolya (Watabou); its credits on the About screen are unchanged. The browser build is the `html` module of [goddeaterr/web-pixeldungeon](https://github.com/goddeaterr/web-pixeldungeon) (libGDX and TeaVM through [gdx-teavm](https://github.com/xpenatan/gdx-teavm)), without that fork's extra floors and title screen.

## What's here

- This branch is upstream commit [`57a4e06`](https://github.com/00-Evan/shattered-pixel-dungeon/commit/57a4e06a4caf162446d1c28caa7983f0493fecf0) (v4.0.2) with our changes on top as commits, so `git log` and the diff against `57a4e06` show everything we changed.
- [`wasmgame/`](../wasmgame) holds the build script, the page the game runs in, the asset packer, and the same changes as `.diff` files against that commit:
  - `web.diff`: the `html` module, and `-PwebOnly`, which builds it without the Android and iOS projects.
  - `ember.diff`: the name on the title and welcome screens, and the game leaves its window to the page around it (no Quit, full screen or screen turning options, no News button without a news service).

## Building

Needs git, JDK 17 or later and python3. In an empty folder:

```sh
bash /path/to/this/repo/wasmgame/build.sh
```

It clones upstream at `57a4e06`, applies both diffs, runs `./gradlew -PwebOnly html:dist`, and writes `out/`: `spd.js`, `scripts/`, `game.html` and the asset packs from `pack.py` (the assets without the music, gzip compressed, and the music as Ogg Vorbis and as MP3).

## Running it

`game.html` answers the game's asset requests from the packs, keeps the game's files in IndexedDB as gdx-teavm does, and lets the page around it read and replace them. On wasmga.me the play page stores those files as the player's save.

## Licence

Same as upstream: GPL-3.0, see [LICENSE.txt](../LICENSE.txt). Our changes are under the same licence as the files they change.
