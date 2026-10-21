#!/bin/bash
# Builds Ember Pixel Dungeon (Shattered Pixel Dungeon v4.0.2, renamed) for the browser, into ./out:
#   spd.js, scripts/   the game, compiled from Java with TeaVM 0.15 through gdx-teavm 1.6.2,
#                      and gdx-teavm's scripts (pixmaps, FreeType, howler.js)
#   game.html          the page the play page runs the game in
#   ember-assets-<hash>.pak.gz, ember-music-ogg-<hash>.pak, ember-music-mp3-<hash>.pak
#                      the game's assets (served from R2), made by pack.py
# web.diff adds goddeaterr/web-pixeldungeon's browser module (html/) and lets it build without
# the Android and iOS projects; ember.diff renames the game and leaves its window to the page.
# Needs git, JDK 17 or later and python3. Run in an empty folder.
set -euo pipefail
here=$(cd "$(dirname "$0")" && pwd)

git clone -q https://github.com/00-Evan/shattered-pixel-dungeon spd
git -C spd checkout -q 57a4e06a4caf162446d1c28caa7983f0493fecf0
git -C spd apply "$here/web.diff"
git -C spd apply "$here/ember.diff"
(cd spd && ./gradlew --no-daemon -PwebOnly html:dist)

build=$(echo spd/html/build/dist/web/b/*/)
mkdir -p out/scripts
cp "$build/spd.js" out/
cp "$build"/scripts/* out/scripts/
cp "$here/game.html" out/
python3 "$here/pack.py" "$build/assets" out
ls -l out
