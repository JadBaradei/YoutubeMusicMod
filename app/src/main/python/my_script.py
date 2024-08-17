from ytmusicapi import YTMusic
import json



def get_song_value(oauth_file_path):

    try:
        yt = YTMusic(oauth_file_path)
    except Exception as e:
        return "what is the json?"

    try:

        song = yt.get_song("6eW99oNNRvI")

        return song.get("videoDetails").get("title")
    except Exception as e:
        return f"An error occured: {e}"


print(get_song_value("oauth.json"))


