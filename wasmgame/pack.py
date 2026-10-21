#!/usr/bin/env python3
"""Packs the game's assets for the play page: pack.py <assets folder> <out folder>

  ember-assets-<hash>.pak.gz   everything but the music, gzip compressed (read before the start)
  ember-music-ogg-<hash>.pak   the music as Ogg Vorbis, streamed while playing
  ember-music-mp3-<hash>.pak   the music as MP3, for browsers without Vorbis (Safari)

A pak is a 4 byte little-endian header length, a JSON list of [path, size] and then
the files' bytes in that order; game.html reads them. The hash is of the pak's bytes.
"""
import gzip, hashlib, json, os, struct, sys

src, out = sys.argv[1], sys.argv[2]
os.makedirs(out, exist_ok=True)

def collect(keep):
    found = []
    for root, _, names in os.walk(src):
        for name in names:
            path = os.path.relpath(os.path.join(root, name), src).replace(os.sep, "/")
            if keep(path):
                found.append(path)
    return sorted(found)

def pak(paths):
    data = [open(os.path.join(src, p), "rb").read() for p in paths]
    head = json.dumps([[p, len(d)] for p, d in zip(paths, data)], separators=(",", ":")).encode()
    return struct.pack("<I", len(head)) + head + b"".join(data)

def write(name, ext, data):
    path = os.path.join(out, "%s-%s%s" % (name, hashlib.sha256(data).hexdigest()[:12], ext))
    open(path, "wb").write(data)
    print(path, len(data))

# gzip without a file name or time, so the same files give the same bytes
write("ember-assets", ".pak.gz", gzip.compress(pak(collect(lambda p: not p.startswith("music/"))), 9, mtime=0))
write("ember-music-ogg", ".pak", pak(collect(lambda p: p.startswith("music/") and p.endswith(".ogg"))))
write("ember-music-mp3", ".pak", pak(collect(lambda p: p.startswith("music/") and p.endswith(".mp3"))))
