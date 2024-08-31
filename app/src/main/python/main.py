import ytmusicapi
import sys
import os
from com.example.youtubemusicmod.utils import AssetHelper #type: ignore

yt = None

def setup_oauth(context):
    original_stdout = sys.stdout  # Save the original stdout
    AssetHelper.createInternalFile(context, "output.txt", "")
    log_file = open(AssetHelper.getInternalFilePath(context,"output.txt"), 'w')
    try:
        sys.stdout = log_file  # Redirect stdout to the log file
        AssetHelper.createInternalFile(context, "oauth.json", "")
        ytmusicapi.setup_oauth(AssetHelper.getInternalFilePath(context,"oauth.json"))
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        sys.stdout = original_stdout  # Restore stdout
        log_file.close()  # Close the log file

def initialize(context):
    global yt
    oauth_file = open(AssetHelper.getInternalFilePath(context,"output.txt"), 'r')
    yt = ytmusicapi.YTMusic(oauth_file)

def is_initialized():
    try:
        # Attempt to fetch the user's playlists
        suggestions = yt.get_search_suggestions()
        return suggestions is not None
    except Exception as e:
        print(f"Initialization check failed: {e}")
        return False


def get_song(song_name):
    try:
        search = yt.search(song_name, filter="songs", limit=1)
        videoId = search.pop(0).get("videoId")
        song = yt.get_song(videoId)
        return song.get("videoDetails").get("title")
    except Exception as e:
        return f"Error while getting song title: {e}"
