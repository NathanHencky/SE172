import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws TwitterException {
        // write your code here

    	
    
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)

        //ENTER YOUR TWITTER KEYS HERE. NOTE, AFTER SEVERAL (1 OR 2) APIC CALLS IT GIVES ERROR. 
        
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        TwitterFactory twitterFactory = new TwitterFactory(cf.build());

        twitter4j.Twitter twitter = twitterFactory.getInstance();


        
    
//API Call 1: Shows the tweets on a User's Profile 
//Code by Param 
        List<Status> status = twitter.getHomeTimeline();


        // show user timeline
//        for(Status st: status){
//
//            System.out.println(st.getUser().getName() + "---->" + st.getText());
//        }
        System.out.println("Here are the tweets:");
        System.out.println();
        for (int i = 0; i < status.size(); i++) {

            Status showStatus = status.get(i);


            System.out.println(showStatus.getUser().getName() + "----> " + showStatus.getText());

        }

        
//API Call 2: Post Tweet 
//Code by Param
        
        System.out.println("Post Tweet");
        System.out.println();


        Status sample = twitter.updateStatus("CMPE 172");
        System.out.println(sample.getText());

        
//API Call 3: Search Tweets by Keyword 
//Code by Puneet

        System.out.println("Search tweets");
        System.out.println();

        Query query = new Query("Life");
        QueryResult result = twitter.search(query);
        for (Status st : result.getTweets()) {
            System.out.println("@" + st.getUser().getScreenName() + ":" + st.getText());
        }
        System.out.println(result.getTweets().stream()
                .map(item -> item.getText())
                .collect(Collectors.toList()));


        
//API Call 4: Get Followers and the Location of the followers
//Code by Puneet 
        
        
        
// get followers
        System.out.println("Get followers");
        System.out.println();

       // TwitterFactory factory = new TwitterFactory();

        String usernameForTwitter;

        usernameForTwitter = twitter.getScreenName();

        IDs idFollower = twitter.getFollowersIDs(usernameForTwitter, -1);
        long[] getIds = idFollower.getIDs();
        
        for (long x : getIds) {
            twitter4j.User user = twitter.showUser(x);
            
            //here i am trying to fetch the followers of each id
            
            String userScreenName = user.getScreenName();
            
            System.out.println("Name: " + user.getScreenName());
            
            System.out.println("Location: " + user.getLocation());

            IDs followerIDsOfFollowers = twitter.getFollowersIDs(user.getScreenName(), -1);
            long[] fofIDs = followerIDsOfFollowers.getIDs();
            for (long subId : fofIDs) {
                twitter4j.User user1 = twitter.showUser(subId);
                System.out.println("Follower Master:" + userScreenName + " Follower of Follower Name: " + user1.getScreenName());
                System.out.println("Location:" + user1.getLocation());

            }

        }

        
//API Call 5: Gets trending in different locations and shows the exact location on earth 
   //Code by Arselan
        // Get trends
   

          //  Twitter twitter = new TwitterFactory().getInstance();
            ResponseList<Location> loc;
            loc = twitter.getAvailableTrends();
            System.out.println("Showing available trends");
            for (Location x : loc) {

                // woeid = where on earth identifier
                System.out.println(x.getName() + " (woeid:" + x.getWoeid() + ")");
            }
            System.out.println("done.");
        //    System.exit(0);

            
//API Call 6: Update's the user's profile pic
//Code by Arselan
            
            
            
      //   Update profile pic

        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.account.UpdateProfileImage [image file path]");
            //        System.exit(-1);
        }


        twitter.updateProfileImage(new File(args[0]));
        System.out.println("Successfully updated profile image.");
        //        System.exit(0);

        
//API Call 7: Sending a Direct Message to a user
//Code by Nathan 
      //   send direct message
        String directMessage = "Hi Param, this is just a test message.";

        String twitterName = "@Paramb0859";
//
//        if (args.length < 2) {
//            System.out.println("Usage: java twitter4j.examples.directmessage.SendDirectMessage [recipient screen name] [message]");
//            System.exit(-1);
//        }
        
        //Here we are sending direct message to Param 
        
        DirectMessage message = twitter.sendDirectMessage("@Paramb0859", directMessage);
        System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
        //     System.exit(0);


//API Call 8: Showing tweets of a specified user, versus your own user. Also, it shows the commments for specified user. 
        // get user tweets
        
//Code by Nathan
        int temp = 1;
        String user = "Paramb0859";
        List listTweets = new ArrayList();

        while (true) {

                int size = listTweets.size();
                
                
                Paging page = new Paging(temp++, 20);
                
                //Displaying the tweets 
            System.out.println(twitter.getUserTimeline(user, page));


        }

    }


}


