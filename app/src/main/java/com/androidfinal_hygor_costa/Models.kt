package com.androidfinal_hygor_costa

//Created by Hygor

//region return JSon for one user

// link https://api.github.com/search/users?q=android

/*

{
total_count: 73824,
incomplete_results: false,
items: [
{
login: "android",
id: 32689599,
node_id: "MDEyOk9yZ2FuaXphdGlvbjMyNjg5NTk5",
avatar_url: "https://avatars.githubusercontent.com/u/32689599?v=4",
gravatar_id: "",
url: "https://api.github.com/users/android",
html_url: "https://github.com/android",
followers_url: "https://api.github.com/users/android/followers",
following_url: "https://api.github.com/users/android/following{/other_user}",
gists_url: "https://api.github.com/users/android/gists{/gist_id}",
starred_url: "https://api.github.com/users/android/starred{/owner}{/repo}",
subscriptions_url: "https://api.github.com/users/android/subscriptions",
organizations_url: "https://api.github.com/users/android/orgs",
repos_url: "https://api.github.com/users/android/repos",
events_url: "https://api.github.com/users/android/events{/privacy}",
received_events_url: "https://api.github.com/users/android/received_events",
type: "Organization",
site_admin: false,
score: 1
}


 */


//endregion

data class ResponseDataClass(
    val incomplete_results: Boolean = false,
    val items: ArrayList<Users>,
    val total_count: Int = 0
)

data class Users(
    val avatar_url: String = "",
    val events_url: String = "",
    val followers_url: String = "",
    val following_url: String = "",
    val gists_url: String = "",
    val gravatar_id: String = "",
    val html_url: String = "",
    val id: Int = 0,
    val login: String = "",
    val node_id: String = "",
    val organizations_url: String = "",
    val received_events_url: String = "",
    val repos_url: String = "",
    val score: Double = 0.0,
    val site_admin: Boolean = false,
    val starred_url: String = "",
    val subscriptions_url: String = "",
    val type: String = "",
    val url: String = ""
)


