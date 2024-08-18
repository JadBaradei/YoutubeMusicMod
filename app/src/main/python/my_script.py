from ytmusicapi import YTMusic
import json

yt = None

def initialize(oauth_file_path):
    global yt
    try:
        yt = YTMusic(oauth_file_path)
    except Exception as e:
        return f"Could not find authentication file{e}"


def get_song(song_name):
    try:
        search = yt.search(song_name, filter="songs", limit=1)
        videoId = search.pop(0).get("videoId")
        song = yt.get_song(videoId)
        return song.get("videoDetails").get("title")
    except Exception as e:
        return f"Error while getting song title: {e}"


# initialize("oauth.json")
# get_song("Hello it's me")
