from collections import defaultdict

# given a graph of users --> who they are following and
# a graph user --> tweet_id they liked, returns a sorted
# array of tweet_ids to recommend to the targetUser that have
# been liked >= given threshhold. A recommended tweet is one
# where it is like by people you follow (ignoring the likes from
# users you don't follow)

def getRecommendedTweets(followGraph_edges, likeGraph_edges, targetUser, minLikeThreshold):
    targetIsFollowing = []
    for user, following in followGraph_edges:
        if user == targetUser:
            targetIsFollowing.append(following)
            
    tweetToLikes = defaultdict(int) # tweetId to total number of likes from people targetUser is following
    tweets_passing_thresh = []
    for user, tweet_id in likeGraph_edges:
        if user in targetIsFollowing:
            tweetToLikes[tweet_id] += 1
            if tweetToLikes[tweet_id] >= minLikeThreshold:
                tweets_passing_thresh.append(tweet_id)
    
    return sorted(tweets_passing_thresh)
