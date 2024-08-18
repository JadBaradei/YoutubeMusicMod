import ytmusicapi
import sys
import os

def get_path(filename):
    path = os.path.join('/data/user/0/com.example.youtubemusicmod/files', filename)
    with open(path, 'r') as reading:
        return reading.read()


def get_oauth():
    log_file = open(get_path("output.txt"), 'w')
    sys.stdout = log_file
    ytmusicapi.setup_oauth(get_path("oauth.json"))

yt = None

def initialize(oauth_file_path):
    global yt
    try:
        yt = ytmusicapi.YTMusic(oauth_file_path)
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

get_oauth()