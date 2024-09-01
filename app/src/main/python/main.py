from ytmusicapi import YTMusic
import json
import os

yt = None
home_data = None

def yt_init_here():
    global yt
    yt = YTMusic('main/python/oauth.json')

def yt_init(oauthFileContent):
    global yt
    try:
        yt = YTMusic(oauthFileContent)
        return "Success"
    except FileNotFoundError:
        return "file not found"
    except Exception as e:
        return "Error occured"

def get_home():
    global home_data
    home_data = yt.get_home(limit=5)
    
def get_listen_again():
    if home_data is None:
        get_home()
    contents = next((item['contents'] for item in home_data if item['title'] == 'Listen again'), None)
    return json.dumps(contents)

def get_quick_picks():
    if home_data is None:
        get_home()
    return next((item for item in home_data if item['title'] == 'Quick picks'), None)

def outputJson(text, file_name):
    with open(file_name, 'w') as file:
        json.dump(text, file, indent=4)

def get_song(song_name):
    try:
        search = yt.search(song_name, filter="songs", limit=1)
        videoId = search.pop(0).get("videoId")
        song = yt.get_song(videoId)
        return song.get("videoDetails").get("title")
    except Exception as e:
        return f"Error while getting song title: {e}"
    
# yt_init_here()
# outputJson(get_listen_again(), 'listenagain.json')